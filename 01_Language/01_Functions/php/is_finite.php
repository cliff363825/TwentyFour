<?php

echo sqrt(2)."\n";
var_dump(is_finite(sqrt(2)));

echo log(0)."\n"; // -INF
var_dump(is_finite(log(0)));

echo asin(2)."\n"; // NAN
var_dump(is_finite(asin(2)));

var_dump(is_finite(null));

