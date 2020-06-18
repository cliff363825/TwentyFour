<?php
// php函数返回值为数组的时候，赋值不会重置数组内部指针。
// php<7.0
function test()
{
    $arr2 = [1, 2, 3];
    foreach ($arr2 as $a) {
    }
    return $arr2;
}

$arr4 = test();
// bool(false)
var_dump(current($arr4));
