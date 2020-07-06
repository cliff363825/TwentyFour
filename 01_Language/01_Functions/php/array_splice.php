<?php

$input = array("red", "green", "blue", "yellow");
var_dump(array_splice($input, -1));
//var_dump(array_splice($input, -5));
//array_splice($input, 0);
var_dump($input);

$input = array("red", "green", "blue", "yellow");
var_dump(array_splice($input, 1, -5));
//array_splice($input, 1, -1);
var_dump($input);

$input = array("red", "green", "blue", "yellow");
array_splice($input, 1, count($input), "orange");
var_dump($input);

$input = array("red", "green", "blue", "yellow");
array_splice($input, -1, 1, array("black", "maroon"));
var_dump($input);
