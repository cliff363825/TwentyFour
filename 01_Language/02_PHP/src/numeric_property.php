<?php
// php中，建立数字字符串为key的数组
$arr = new stdClass();
$key = 1;
$arr->$key = 'hehe';
var_dump(['1' => 'haha']);
var_dump($arr);
