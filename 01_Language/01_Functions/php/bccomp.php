<?php

echo bccomp('1', '2') . "\n";   // -1
echo bccomp('1.00001', '1', 3) . "\n"; // 0
echo bccomp('1.00001', '1', 5) . "\n"; // 1
