<?php
// php中，break 和 continue后面都是可以加层级的；而且在5.4.0版本之后只能为`正数`或者`值为正数的内置常量(包括扩展中定义的常量)`
const ONE = 1;
define('ONEX', 1);

var_dump(constant('ONE'), constant('ONEX'));
var_dump(E_ERROR);

foreach (range(0, 10) as $i) {
    foreach (range(0, 10) as $j) {
        echo "$i * $j" . PHP_EOL;
        // break ONE; // php<5.4
        // break ONEX; // php<5.4
        // break E_ERROR; // php<7
    }
}
