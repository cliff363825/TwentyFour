<?php

$x = 10;
$y = 2;
$mod = 3;
$a = bcpowmod($x, $y, $mod);

$b = bcmod(bcpow($x, $y), $mod);

// $a and $b are equal to each other.
var_dump($a, $b);
