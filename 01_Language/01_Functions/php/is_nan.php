<?php

// Invalid calculation, will return a
// NaN value
$nan = acos(8);

var_dump($nan, is_nan($nan));
