# coding: utf-8
import os
import sys
import subprocess
import argparse

# svn log -r {2018-03-15}:{2018-03-16}|sed -n '/your_name/,/----$/p' |grep 'vc stable' |awk '{print $3}' |sort -n|uniq -c
# git svn log -r 12000:12263 -v|sed -n '/Changed paths:/,/^$/p'|grep -E '^\s+'|awk '{print $2}'

parser = argparse.ArgumentParser(description="svn comparison and automatic release script")
parser.add_argument("-p", "--path", type=str, help="your svn path", required=True)
parser.add_argument("-l", "--limit", default=100, type=int, help="svn log limit size")
parser.add_argument("--ext", default=".php", type=str, help="filter file extensions, default: .php")
# parser.add_argument("--sync", action="store_true", help="auto synchronize if this option is set")
args = parser.parse_args()

svn_path = args.path
output = []
svn_command = ["svn", "log", "-l", str(args.limit), svn_path]
process = subprocess.Popen(svn_command, stdin=subprocess.PIPE, stdout=subprocess.PIPE)
out_data, err_data = process.communicate()
if process.returncode != 0:
    # exec command fail
    print(out_data, err_data)
    sys.exit(process.returncode)
    # raise Exception(err_data.decode(sys.stdout.encoding, errors="ignore"))

svn_log = out_data.decode(sys.stdout.encoding, errors="ignore")
# vc stable file
stable_prefix = "vc stable "
# vc rc file
rc_prefix = "vc rc "
file_dict = {}
for line in svn_log.split(os.linesep):
    if line.startswith(stable_prefix):
        file_state = 1
        file = line[len(stable_prefix):].strip()
        _, file_ext = os.path.splitext(file)
    elif line.startswith(rc_prefix):
        file_state = 0
        file = line[len(rc_prefix):].strip()
        _, file_ext = os.path.splitext(file)
    else:
        continue
    if file not in file_dict and file_ext in args.ext.split(','):
        file_dict[file] = file_state

sync_file_list = [k for k, v in file_dict.items() if v == 0]

if len(sync_file_list) == 0:
    print("all files is already sync, exit")
    sys.exit(0)

print("change file list:")
print(os.linesep.join(sync_file_list))

change_flag = input("modify files? [yes|no]: ").lower()
if change_flag not in ['yes', 'y']:
    print("no changes, exit")
    sys.exit(0)

stable_files = []
for f in sync_file_list:
    file_path = os.path.realpath(os.path.join(svn_path, f.strip('\\/')))
    if not os.path.isfile(file_path):
        print("%s is not a file, skip" % file_path)
        continue
    try:
        with open(file_path, mode='r', encoding="utf-8") as cur_file:
            lines = cur_file.readlines()
    except UnicodeDecodeError as e:
        continue
    if len(lines) == 0:
        print("file content is empty, skip")
        continue
    lines[0] = lines[0].strip('\r\n') + " " + "\n"
    with open(file_path, mode='w', encoding="utf-8") as cur_file:
        cur_file.writelines(lines)
    del lines
    stable_files.append(f)

# sync_flag = input("sync files? [yes|no]: ").lower()
# if sync_flag not in ['yes', 'y']:
print("generate comments:")
cmd = [stable_prefix + f for f in stable_files]
print("\n".join(cmd))
