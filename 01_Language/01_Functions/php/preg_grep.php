<?php

$array = [
    1.23,
    .45,
    'abc'
];
// return all array elements
// containing floating point numbers
$fl_array = preg_grep("/^(\d+)?\.\d+$/", $array);
var_dump($fl_array);

$fl_array = preg_grep("/\d+/", $array);
var_dump($fl_array);
