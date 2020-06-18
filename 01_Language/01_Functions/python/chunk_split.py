# coding: utf-8

import textwrap


def chunk_split(body, chunklen=76, end="\r\n"):
    return end.join(textwrap.wrap(body, chunklen))


if __name__ == '__main__':
    from base64_encode import base64_encode

    # format $data using RFC 2045 semantics
    data = "Returns the chunked string."
    print(repr(chunk_split(base64_encode(data) * 5)))
