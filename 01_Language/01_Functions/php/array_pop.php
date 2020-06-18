<?php

$stack = array("orange", "banana", "apple", "raspberry");
$fruit = array_pop($stack);
print_r($stack);

$map = array("k1" => "orange", "k2" => "banana", "k3" => "apple", "k4" => "raspberry");
$fruit = array_pop($map);
print_r($map);
print_r($fruit);
