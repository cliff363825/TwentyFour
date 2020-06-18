<?php

bcscale(0);
echo bcmod( '5',  '3') . "\n"; //  2
echo bcmod( '5', '-3') . "\n"; //  2
echo bcmod('-5',  '3') . "\n"; // -2
echo bcmod('-5', '-3') . "\n"; // -2

bcscale(1);
echo bcmod('5.7', '1.3'); // 0.5 as of PHP 7.2.0; 0 previously
