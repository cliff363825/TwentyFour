function intval(v, base) {
    let type = typeof v
    if (type === 'number') {
        return parseInt(v)
    } else if (type === 'string') {
        return parseInt(v, base || 10)
    } else if (type === 'boolean') {
        return v ? 1 : 0
    } else if (Object.prototype.toString.call(v) === '[object Array]') {
        return v.length === 0 ? 0 : 1
    }
    return 0
}

//        echo intval(42) . "\n";                      // 42
//        echo intval(4.2) . "\n";                     // 4
//        echo intval('42') . "\n";                    // 42
//        echo intval('+42') . "\n";                   // 42
//        echo intval('-42') . "\n";                   // -42
//        echo intval(042) . "\n";                     // 34
//        echo intval('042') . "\n";                   // 42
//        echo intval(1e10) . "\n";                    // 10000000000
//        echo intval('1e10') . "\n";                  // 10000000000
//        echo intval(0x1A) . "\n";                    // 26
//        echo intval(42000000) . "\n";                // 42000000
//        echo intval(420000000000000000000) . "\n";   // -4275113695319687168
//        echo intval('420000000000000000000') . "\n"; // 9223372036854775807
//        echo intval(42, 8) . "\n";                   // 42
//        echo intval('42', 8) . "\n";                 // 34
//        echo intval(array()) . "\n";                 // 0
//        echo intval(array('foo', 'bar')) . "\n";     // 1
//        echo intval(false) . "\n";                   // 0
//        echo intval(true) . "\n";                    // 1
console.log(intval(42))
console.log(intval(4.2))
console.log(intval('42'))
console.log(intval('+42'))
console.log(intval('-42'))
console.log(intval(0o42))
console.log(intval('042'))
console.log(intval(1e10))
console.log(intval('1e10'))
console.log(intval(0x1A))
console.log(intval(42000000))
console.log(intval(420000000000000000000))
console.log(intval('420000000000000000000'))
console.log(intval(42, 8))
console.log(intval('42', 8))
console.log(intval([]))
console.log(intval(["foo", "bar"]))
console.log(intval(false))
console.log(intval(true))
console.log(intval(null))
