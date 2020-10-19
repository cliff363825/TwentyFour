<?php

echo intval(42) . "\n";                      // 42
echo intval(4.2) . "\n";                     // 4
echo intval('42') . "\n";                    // 42
echo intval('+42') . "\n";                   // 42
echo intval('-42') . "\n";                   // -42
echo intval(042) . "\n";                     // 34
echo intval('042') . "\n";                   // 42
echo intval(1e10) . "\n";                    // 10000000000
echo intval('1e10') . "\n";                  // 10000000000
echo intval(0x1A) . "\n";                    // 26
echo intval(42000000) . "\n";                // 42000000
echo intval(420000000000000000000) . "\n";   // -4275113695319687168
echo intval('420000000000000000000') . "\n"; // 9223372036854775807
echo intval(42, 8) . "\n";                   // 42
echo intval('42', 8) . "\n";                 // 34
echo intval(array()) . "\n";                 // 0
echo intval(array('foo', 'bar')) . "\n";     // 1
echo intval(false) . "\n";                   // 0
echo intval(true) . "\n";                    // 1
