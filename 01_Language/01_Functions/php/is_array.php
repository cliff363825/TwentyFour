<?php

$yes = array('this', 'is', 'an array');

echo is_array($yes) ? 'Array' : 'not an Array';
echo "\n";

$no = 'this is a string';

echo is_array($no) ? 'Array' : 'not an Array';
echo "\n";

$map = ["a" => 1, "b" => 2];
echo is_array($map) ? "true" : "false";
echo "\n";
