# coding: utf-8

import html


def htmlspecialchars_decode(string):
    return html.unescape(string)
