<?php

echo sqrt(2)."\n";
var_dump(is_infinite(sqrt(2))); // false

echo log(0)."\n"; // -INF
var_dump(is_infinite(log(0))); // true

echo asin(2)."\n"; // NAN
var_dump(is_infinite(asin(2))); // false

var_dump(is_infinite(null)); // false
