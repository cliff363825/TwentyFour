<?php

echo max(2, 3, 1, 6, 7) . "\n";  // 7
echo max(array(2, 4, 5)) . "\n"; // 5

// The string 'hello' when compared to an int is treated as 0
// Since the two values are equal, the order they are provided determines the result
echo max(0, 'hello') . "\n";     // 0
echo max('hello', 0) . "\n";     // hello

// Here we are comparing -1 < 0, so 'hello' is the highest value
echo max('hello', -1) . "\n";    // hello

// With multiple arrays of different lengths, max returns the longest
$val = max(array(2, 2, 2), array(1, 1, 1, 1)); // array(1, 1, 1, 1)
var_dump($val);

// Multiple arrays of the same length are compared from left to right
// so in our example: 2 == 2, but 5 > 4
$val = max(array(2, 4, 8), array(2, 5, 1)); // array(2, 5, 1)
var_dump($val);

// If both an array and non-array are given, the array will be returned
// as comparisons treat arrays as greater than any other value
$val = max('string', array(2, 5, 7), 42);   // array(2, 5, 7)
var_dump($val);

// If one argument is NULL or a boolean, it will be compared against
// other values using the rule FALSE < TRUE regardless of the other types involved
// In the below example, -10 is treated as TRUE in the comparison
$val = max(-10, FALSE); // -10
var_dump($val);

// 0, on the other hand, is treated as FALSE, so is "lower than" TRUE
$val = max(0, TRUE); // TRUE
var_dump($val);
