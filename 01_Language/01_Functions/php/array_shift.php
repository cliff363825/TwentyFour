<?php

$stack = array("orange", "banana", "apple", "raspberry");
$fruit = array_shift($stack);
print_r($stack);

$stack = array("k1" => "orange", "k2" => "banana", "apple", "raspberry");
$fruit = array_shift($stack);
print_r($stack);
