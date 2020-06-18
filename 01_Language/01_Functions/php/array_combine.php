<?php

$a = array('green', 'red', 'yellow');
$b = array('avocado', 'apple', 'banana');
$c = array_combine($a, $b);

print_r($c);

$a = ['a1' => 'k1', 'a2' => 'k2', 'a3' => 'k3'];
$b = ['a2' => 'v2', 'a3' => 'v3', 'a1' => 'v1'];
$c = array_combine($a, $b);
print_r($c);
