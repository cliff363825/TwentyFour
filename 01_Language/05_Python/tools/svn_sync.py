# coding: utf-8
import argparse
import logging
import os
import subprocess
from configparser import ConfigParser

import paramiko
import sys

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)-8s %(filename)s:%(lineno)-4d: %(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S')


class Project(object):
    def __init__(self, name):
        """
        :type name: str 项目名称
        """
        super(Project, self).__init__()

        # 加载配置文件
        config = ConfigParser()
        config.read(os.path.realpath(os.path.join(os.path.dirname(__file__), 'svn_sync.ini')))
        # 初始化
        self.host = config.get(section=name, option='host', fallback=None)
        self.port = config.get(section=name, option='port', fallback=None)
        self.user = config.get(section=name, option='user', fallback=None)
        self.pkey = config.get(section=name, option='pkey', fallback=None)
        self.remote = config.get(section=name, option='remote', fallback=None)
        self.local = config.get(section=name, option='local', fallback=None)
        self.cmd = config.get(section='config', option='cmd', fallback=None)
        # 校验参数
        for k in ['host', 'port', 'user', 'pkey', 'remote', 'local', 'cmd']:
            if not getattr(self, k):
                raise AttributeError('[%s].%s can not be empty.' % (name, k))

        self.local = os.path.abspath(self.local)
        # 更改工作目录
        os.chdir(self.local)

    def push_revision(self, revision):
        cmd = "%s log -r %s -v|sed -n '/Changed paths:/,/^$/p'|grep -E '^\s+.*$'|awk '{print $2}'|sort -n|uniq -c" % (
            self.cmd, revision)
        logging.info(cmd)
        process = subprocess.Popen(cmd, shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE)
        out_data, err_data = process.communicate()
        if process.returncode != 0:
            # 执行失败
            print(out_data, err_data)
            sys.exit(process.returncode)
        out_str = out_data.decode('utf-8')
        logging.info(out_str)
        for f in out_str.split('\n'):
            if f.strip() != '':
                self.push_file(f)

    def push_file(self, file):
        """
        :type file: str
        """
        file_list = []
        file_path = os.path.abspath(os.path.join(self.local, file.strip(' \\/')))
        if os.path.isdir(file_path):
            for dirpath, dirnames, filenames in os.walk(file_path):
                for filename in filenames:
                    file_list.append(os.path.join(dirpath, filename).replace(self.local, '', 1))
        elif os.path.isfile(file_path):
            file_list.append(file)
        else:
            raise Exception('path not found: %s' % file_path)

        t = None
        try:
            k = paramiko.RSAKey.from_private_key_file(self.pkey)
            t = paramiko.Transport((self.host, int(self.port)))
            t.connect(username=self.user, pkey=k)
            sftp = paramiko.SFTPClient.from_transport(t)
            for f in file_list:
                local_path = os.path.abspath(os.path.join(self.local, f.strip(' \\/')))
                server_path = os.path.join(self.remote, f.strip(' \\/')).replace('\\', '/')
                if os.path.isfile(local_path):
                    yn = input('push file %s (y/n): ' % local_path)  # type: str
                    if yn == 'y':
                        sftp.put(local_path, server_path)
                    else:
                        logging.info('skip file: %s' % local_path)
                else:
                    logging.warning('file not found: %s' % local_path)
        except Exception as e:
            logging.error(e)
        finally:
            if t:
                t.close()


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Svn Code Synchronized')
    parser.add_argument('-p', '--project', type=str, help='Project Name', required=True)
    parser.add_argument('-r', '--revision', type=str, help='Revision')
    parser.add_argument('file', type=str, help='File Or Directory', nargs='*')
    args = parser.parse_args()

    p = Project(name=args.project)
    if args.revision:
        p.push_revision(args.revision)
    elif args.file:
        for f in args.file:
            p.push_file(f)
    else:
        parser.error('no files')
