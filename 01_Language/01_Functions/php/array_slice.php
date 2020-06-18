<?php

$input = array("a", "b", "c", "d", "e");

$output = array_slice($input, 2);      // returns "c", "d", and "e"
$output = array_slice($input, -2, 1);  // returns "d"
$output = array_slice($input, -1, 2);  // returns "e"
$output = array_slice($input, 0, 3);   // returns "a", "b", and "c"
$output = array_slice($input, 1, -5); // []

// note the differences in the array keys
print_r(array_slice($input, 2, -1));
print_r(array_slice($input, 2, -1, true));
