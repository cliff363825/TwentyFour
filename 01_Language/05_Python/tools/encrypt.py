#!/usr/bin/python
# coding: utf-8
import re
import sys
from optparse import OptionParser


class MyEncrypt:
    def encrypt_v4(self, enc_id):
        s1 = (int(enc_id) + 201341) * 7
        l = len(str(s1))
        lb = int(l / 2)
        lc = l - lb
        a = str(s1)[0:lb]
        b = str(s1)[lb:]
        tmpstr = ''
        for i in range(lb):
            if i % 2 == 0:
                tmpstr += a[int(i / 2)] + b[lc - 1 - int(i / 2)]
            else:
                tmpstr += b[int(i / 2)] + a[lb - 1 - int(i / 2)]
        if l % 2 == 1:
            tmpstr += b[int((lc - 1) / 2)]
        return tmpstr

    def decrypt_v4(self, dec_id):
        dec_id = str(dec_id)
        l = len(dec_id)
        tmpstr = dict()
        flag = 1
        c = 0
        for i in range(l):
            if i != 0 and i % 2 == 0:
                flag = -flag
                if flag == 1:
                    c += 1
            if i == l - 1:
                for j in range(l + 1):
                    if tmpstr.get(j) is None:
                        tmpstr[j] = dec_id[i]
                        break
            else:
                if i % 2 == 0:
                    if flag == 1:
                        tmpstr[int(i / 2) - c] = dec_id[i]
                    else:
                        tmpstr[int(l / 2) + c] = dec_id[i]
                else:
                    if flag == 1:
                        tmpstr[l - int((i - c * 2) / 2) - 1] = dec_id[i]
                    else:
                        tmpstr[int(l / 2) - 1 - c] = dec_id[i]

        tmpstr = self.ksort(tmpstr)
        tmpstr = "".join([item[1] for item in tmpstr])
        tmpstr = int(tmpstr) / 7 - 201341
        if isinstance(tmpstr, float) or tmpstr < 0:
            return None
        else:
            return tmpstr

    def ksort(self, d):
        return [(k, d[k]) for k in sorted(d.keys())]


def my_callback(option, opt, value, parser):
    setattr(parser.values, option.dest, value.split(','))


def main():
    parser = OptionParser()
    parser.add_option("-e", "--enc", dest="enc_id",
                      type='string',
                      action='callback',
                      callback=my_callback,
                      help=u"ID加密，多个请使用英文逗号分隔")
    parser.add_option("-d", "--dec", dest="dec_id",
                      type='string',
                      action='callback',
                      callback=my_callback,
                      help=u"ID解密，多个请使用英文逗号分隔")

    (options, args) = parser.parse_args()
    if not options.enc_id and not options.dec_id:
        parser.print_help()
        return
    if options.enc_id:
        for i in options.enc_id:
            print MyEncrypt().encrypt_v4(i),
    if options.dec_id:
        for i in options.dec_id:
            print MyEncrypt().decrypt_v4(i),


if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(main())
