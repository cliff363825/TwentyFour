// https://locutus.io/php/network/inet_ntop/index.html
function inet_ntop(inAddr) {
    //  discuss at: https://locutus.io/php/inet_ntop/
    // original by: Theriault (https://github.com/Theriault)
    //   example 1: inet_ntop('\x7F\x00\x00\x01')
    //   returns 1: '127.0.0.1'
    //   _example 2: inet_ntop('\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\1')
    //   _returns 2: '::1'

    var i = 0
    var m = ''
    var c = []

    inAddr += ''
    if (inAddr.length === 4) {
        // IPv4
        return [
            inAddr.charCodeAt(0),
            inAddr.charCodeAt(1),
            inAddr.charCodeAt(2),
            inAddr.charCodeAt(3)
        ].join('.')
    } else if (inAddr.length === 16) {
        // IPv6
        for (i = 0; i < 16; i++) {
            c.push(((inAddr.charCodeAt(i++) << 8) + inAddr.charCodeAt(i)).toString(16))
        }
        return c.join(':')
            .replace(/((^|:)0(?=:|$))+:?/g, function (t) {
                m = (t.length > m.length) ? t : m
                return t
            })
            .replace(m || ' ', '::')
    } else {
        // Invalid length
        return false
    }
}

console.log(inet_ntop("\x7F\x00\x00\x01"))
console.log(inet_ntop("\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\1"))
