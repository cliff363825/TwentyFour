# coding: utf-8

import logging

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)-8s %(filename)s:%(lineno)-4d: %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')

import os
import select

try:
    import SocketServer
except ImportError:
    import socketserver as SocketServer

import sys
from optparse import OptionParser
from configparser import ConfigParser

import paramiko
import threading


class Application(object):
    def __init__(self, **kwargs):
        # server
        self.server_host = kwargs.get('server_host') or kwargs.get('ssh_host')
        self.server_port = kwargs.get('server_port') or kwargs.get('ssh_port')
        self.server_port = int(self.server_port)
        self.server_user = kwargs.get('server_user') or kwargs.get('ssh_user')
        self.server_password = kwargs.get('server_password') or kwargs.get('ssh_password')
        server_pkey = kwargs.get('server_pkey')
        if server_pkey is not None:
            self.pkey = paramiko.RSAKey.from_private_key_file(server_pkey)
        else:
            self.pkey = None

        # local
        self.local_port = kwargs.get('local_port')
        self.local_port = int(self.local_port)

        # remote
        self.remote_host = kwargs.get('remote_host') or kwargs.get('host')
        self.remote_port = kwargs.get('remote_port') or kwargs.get('port')
        self.remote_port = int(self.remote_port)

        # client
        client = paramiko.SSHClient()
        client.load_system_host_keys()
        # client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        client.set_missing_host_key_policy(paramiko.WarningPolicy())
        self.client = client

        self.lock = threading.Lock()
        self.retry_times = int(kwargs.get('retry_times', 5))

    def connect(self):
        logging.info("Connecting to ssh host %s:%d ..." % (self.server_host, self.server_port))
        try:
            self.client.connect(
                self.server_host,
                self.server_port,
                username=self.server_user,
                password=self.server_password,
                pkey=self.pkey
            )
            self.client.get_transport().set_keepalive(60)
        except Exception as e:
            logging.error("*** Failed to connect to %s:%d: %r" % (self.server_host, self.server_port, e))
            raise e


class ForwardServer(SocketServer.ThreadingTCPServer):
    daemon_threads = True
    allow_reuse_address = True


class Handler(SocketServer.BaseRequestHandler):
    def handle(self):
        app = self.get_app()
        retry_times = app.retry_times

        while not app.client.get_transport().is_active() and retry_times > 0:
            app.lock.acquire()
            try:
                if not app.client.get_transport().is_active():
                    app.connect()
            finally:
                retry_times -= 1
                app.lock.release()

        try:
            chan = app.client.get_transport().open_channel(
                "direct-tcpip",
                (app.remote_host, app.remote_port),
                self.request.getpeername(),
            )
        except Exception as e:
            logging.error(
                "Incoming request to %s:%d failed: %s"
                % (app.remote_host, app.remote_port, repr(e))
            )
            return
        if chan is None:
            logging.error(
                "Incoming request to %s:%d was rejected by the SSH server."
                % (app.remote_host, app.remote_port)
            )
            return

        logging.info(
            "Connected!  Tunnel open %r -> %r -> %r"
            % (
                self.request.getpeername(),
                chan.getpeername(),
                (app.remote_host, app.remote_port),
            )
        )
        while True:
            r, w, x = select.select([self.request, chan], [], [])
            if self.request in r:
                data = self.request.recv(1024)
                if len(data) == 0:
                    break
                chan.send(data)
            if chan in r:
                data = chan.recv(1024)
                if len(data) == 0:
                    break
                self.request.send(data)

        peername = self.request.getpeername()
        chan.close()
        self.request.close()
        logging.info("Tunnel closed from %r" % (peername,))

    def set_app(self, app):
        """
        :param Application app:
        :return:
        """
        self._app = app

    def get_app(self):
        return self._app


def forward_tunnel(app):
    # this is a little convoluted, but lets me configure things for the Handler
    # object.  (SocketServer doesn't give Handlers any way to access the outer
    # server normally.)
    logging.info(
        "Now forwarding port %d to %s:%d ..."
        % (app.local_port, app.remote_host, app.remote_port)
    )

    class SubHander(Handler):
        def setup(self):
            Handler.setup(self)
            Handler.set_app(self, app)
            # super(SubHander, self).setup()
            # super(SubHander, self).set_app(app)

    ForwardServer(("", app.local_port), SubHander).serve_forever()


HELP = """\
Set up a forward tunnel across an SSH server, using paramiko.
"""


def parse_options():
    parser = OptionParser(
        usage="usage: %prog -s section_name",
        version="%prog 1.0",
        description=HELP,
    )
    parser.add_option(
        "-s",
        "--section",
        action="store",
        type="string",
        dest="section",
        default=None,
        help="config section",
    )
    parser.add_option(
        "--local-port",
        action="store",
        type="int",
        dest="local_port",
        default=None,
        help="local port",
    )
    parser.add_option(
        "--remote-host",
        action="store",
        type="string",
        dest="remote_host",
        default=None,
        help="remote host",
    )
    parser.add_option(
        "--remote-port",
        action="store",
        type="int",
        dest="remote_port",
        default=None,
        help="remote port",
    )

    options, args = parser.parse_args()
    if options.section is None:
        parser.error("Config section required (-s).")

    config = ConfigParser()
    config.read(os.path.join(os.path.dirname(__file__), 'ssh_forward.ini'))
    params = dict(config.items(options.section))
    if options.local_port is not None:
        params['local_port'] = options.local_port
    if options.remote_host is not None:
        params['remote_host'] = options.remote_host
    if options.remote_port is not None:
        params['remote_port'] = options.remote_port

    return params


def main():
    params = parse_options()
    app = Application(**params)
    app.connect()

    try:
        forward_tunnel(app)
    except KeyboardInterrupt:
        print("C-c: Port forwarding stopped.")
        sys.exit(0)


if __name__ == "__main__":
    main()
