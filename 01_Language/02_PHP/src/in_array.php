<?php
// php 中，`in_array`函数是弱类型比较，强类型比较时，请指定第三个参数为`true`，还可以大大提高程序的性能。

$a = 'hello';

var_dump(in_array($a, [0, 1, 2, 3]));

var_dump(in_array($a, [0, 1, 2, 3], true));
