<?php
// php 中，判断数组的键是否存在时，应使用`array_key_exists`而不是`isset`
$arr = [
    'iamnull' => null,
];
var_dump(isset($arr['iamnull'])); // false
var_dump(array_key_exists('iamnull', $arr)); // true
