<?php

$stack = array("orange", "banana");
array_push($stack, "apple", "raspberry");
print_r($stack);

$stack = array("k1" => "orange", 2 => "banana");
array_push($stack, "apple", "raspberry");
print_r($stack);
