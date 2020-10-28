<?php

echo sqrt(2)."\n";
var_dump(is_finite(sqrt(2))); // true

echo log(0)."\n"; // -INF
var_dump(is_finite(log(0))); // false

echo asin(2)."\n"; // NAN
var_dump(is_finite(asin(2))); // false

var_dump(is_finite(null)); // true

