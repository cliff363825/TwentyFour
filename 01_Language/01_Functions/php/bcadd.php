<?php

$a = '1.2345';
$b = '5';

echo bcadd($a, $b) . "\n";     // 6
echo bcadd($a, $b, 3) . "\n";  // 6.2340
