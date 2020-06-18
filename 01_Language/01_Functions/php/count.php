<?php

$a[0] = 1;
$a[1] = 3;
$a[2] = 5;
var_dump(count($a));

$b[0] = 7;
$b[5] = 9;
$b[10] = 11;
var_dump(count($b));

var_dump(count(null)); // 0
var_dump(count(false)); // 1

var_dump((array)null); // []
var_dump((array)false); // [false]
