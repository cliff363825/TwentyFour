# coding: utf-8

def intval(var, base=10):
    if isinstance(var, (int, float)):
        return int(var)
    elif isinstance(var, bool):
        return 1 if var else 0
    elif isinstance(var, (list, tuple, set, dict)):
        return 0 if len(var) == 0 else 1
    return int(var, base)


if __name__ == '__main__':
    '''
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
    '''
    print(intval(42))
    print(intval(4.2))
    print(intval('42'))
    print(intval('+42'))
    print(intval('-42'))
    print(intval(0o42))
    print(intval('042'))
    print(intval(1e10))
    # print(intval('1e10'))
    print(intval(0x1A))
    print(intval(42000000))
    print(intval(420000000000000000000))
    print(intval('420000000000000000000'))
    print(intval(42, 8))
    print(intval('42', 8))
    print(intval([]))
    print(intval(["foo", "bar"]))
    print(intval(False))
    print(intval(True))
