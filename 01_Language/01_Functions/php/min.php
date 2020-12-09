<?php

echo min(2, 3, 1, 6, 7);  // 1
echo min(array(2, 4, 5)); // 2

// The string 'hello' when compared to an int is treated as 0
// Since the two values are equal, the order they are provided determines the result
echo min(0, 'hello');     // 0
echo min('hello', 0);     // hello

// Here we are comparing -1 < 0, so -1 is the lowest value
echo min('hello', -1);    // -1

// With multiple arrays of different lengths, min returns the shortest
$val = min(array(2, 2, 2), array(1, 1, 1, 1)); // array(2, 2, 2)

// Multiple arrays of the same length are compared from left to right
// so in our example: 2 == 2, but 4 < 5
$val = min(array(2, 4, 8), array(2, 5, 1)); // array(2, 4, 8)

// If both an array and non-array are given, the array is never returned
// as comparisons treat arrays as greater than any other value
$val = min('string', array(2, 5, 7), 42);   // string

// If one argument is NULL or a boolean, it will be compared against
// other values using the rules FALSE < TRUE and NULL == FALSE regardless of the
// other types involved
// In the below examples, both -10 and 10 are treated as TRUE in the comparison
$val = min(-10, FALSE, 10); // FALSE
$val = min(-10, NULL, 10);  // NULL

// 0, on the other hand, is treated as FALSE, so is "lower than" TRUE
$val = min(0, TRUE); // 0
