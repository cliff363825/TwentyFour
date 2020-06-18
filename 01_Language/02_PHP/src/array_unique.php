<?php
// `array_diff` 和 `array_unique` 一样都是比较数组元素的 string 值，`(string) []` -> `Array`，所以用于多维数组是没有意义的。
// Note: Note that array_unique() is not intended to work on multi dimensional arrays.
error_reporting(E_ALL);
var_dump(array_unique([[1], [2]])); // x
