# coding: utf-8
import argparse
import os
import subprocess
from configparser import ConfigParser


class Project(object):
    def __init__(self, name, env):
        """
        初始化
        :type name: str
        """
        super(Project, self).__init__()
        self.svn_path = None
        self.svn_cmd = None
        self.env = None
        try:
            config = ConfigParser()
            config.read(os.path.realpath(os.path.join(os.path.dirname(__file__), 'svn_config.ini')))
            self.svn_path = config.get(section=name, option='svn_path')
            self.svn_cmd = config.get(section='svn', option='svn_cmd')
            self.env = config.get(section=name, option='env_' + env)
        except:
            pass
        if self.svn_path is None or self.svn_path.strip() == '':
            raise Exception('unknown svn_path in section [%s]' % name)
        if self.svn_cmd is None or self.svn_cmd.strip() == '':
            raise Exception('unknown svn_cmd in section [svn]')
        if self.env is None or self.env.strip() != '1':
            raise Exception('unknown environment [%s] in section [%s]' % ('env_' + env, name))
        self.svn_path = os.path.realpath(self.svn_path)

    def publish(self, file, env):
        """
        发布到指定环境
        :type file: str
        :type env: str
        """
        file_list = []
        file_path = os.path.realpath(os.path.join(self.svn_path, file.strip(' \\/')))
        if os.path.isdir(file_path):
            for dirpath, dirnames, filenames in os.walk(file_path):
                for filename in filenames:
                    file_list.append(os.path.join(dirpath, filename).replace(self.svn_path, '', 1))
        elif os.path.isfile(file_path):
            file_list.append(file)
        for f in file_list:
            self._publish(f, env)

    def _publish(self, file, env):
        """
        发布文件到指定环境
        :type file: str
        :type env: str
        """
        file_path = os.path.realpath(os.path.join(self.svn_path, file.strip(' \\/')))
        if not os.path.isfile(file_path):
            raise Exception('file error: %s' % file_path)
        self._svn_up(file_path)
        self._change_file(file_path)
        comment = ' '.join(['vc', env, file]).replace('\\', '/')
        svn_command = [self.svn_cmd, 'commit', '-m', comment, file_path]
        yn = input('commit file: [' + ' '.join(svn_command) + '] (y/n): ')  # type: str
        if yn == 'y':
            process = subprocess.Popen(svn_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE)
            out_data, err_data = process.communicate()
            if process.returncode != 0:
                raise Exception('svn commit error: %s, %s' % (out_data, err_data))
            print('svn commit: %s' % out_data.decode('utf-8'))
        else:
            print('commit cancel: %s' % comment)

    def _svn_up(self, file_path):
        """
        SVN更新
        :type file_path: str
        """
        svn_command = [self.svn_cmd, 'update', file_path]
        process = subprocess.Popen(svn_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE)
        out_data, err_data = process.communicate()
        if process.returncode != 0:
            raise Exception('svn update error: %s, %s' % (out_data, err_data))
        print('svn update: %s' % out_data.decode('utf-8'))

    def _change_file(self, file_path):
        """
        修改文件
        :type file_path: str
        """
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        if content == '':
            raise Exception('file content empty: %s' % file_path)
        content = content.replace('<?php', '<?php ', 1)
        with open(file_path, 'w', encoding='utf-8', newline='\n') as f:
            f.write(content)
        print('change file: %s' % file_path)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='svn automatic release system')
    parser.add_argument('-p', '--project', type=str, help='project name', required=True)
    parser.add_argument('-e', '--env', type=str, help='environment', required=True)
    parser.add_argument('-r', '--requirements', type=str, help='requirement file')
    parser.add_argument('file', type=str, help='file or directory', nargs='*')
    args = parser.parse_args()
    p = Project(name=args.project, env=args.env)
    files = []
    if args.requirements:
        with open(args.requirements) as f:
            files = [x.strip() for x in f.readlines() if x.strip() != '']
    elif args.file:
        files = args.file
    if files:
        for f in files:
            p.publish(f, args.env)
    else:
        parser.error('no files')
