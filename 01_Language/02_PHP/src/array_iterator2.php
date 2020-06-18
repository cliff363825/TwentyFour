<?php
// php中，赋值引发的死循环，因为数组内部指针被重置了。
$arr = ['hello', 'world'];
while ($word = each($arr)) {
    var_dump($word['value']);
    $x = $arr; // here
}
