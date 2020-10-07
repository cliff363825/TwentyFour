# coding: utf-8

from html import entities


def htmlentities(string):
    result = ""
    for s in string:
        if s == "&":
            result += "&amp;"
        elif s == "<":
            result += "&lt;"
        elif s == ">":
            result += "&gt;"
        elif s == '"':
            result += "&quot;"
        elif s == "'":
            result += "&#x27;"
        elif ord(s) in entities.codepoint2name:
            result += "&" + entities.codepoint2name[ord(s)] + ";"
        else:
            result += s
    return result


if __name__ == '__main__':
    print(htmlentities("A 'quote' is <b>bold中国</b>"))
    print(htmlentities("<Il était une fois un être>."))
