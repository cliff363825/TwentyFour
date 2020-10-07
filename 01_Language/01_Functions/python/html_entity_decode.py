# coding: utf-8

import html


def html_entity_decode(string):
    return html.unescape(string)


if __name__ == '__main__':
    print(html_entity_decode("I'll &quot;walk&quot; the &lt;b&gt;dog&lt;/b&gt; now"))
    print(html_entity_decode("&lt;Il &eacute;tait une fois un &ecirc;tre&gt;."))
