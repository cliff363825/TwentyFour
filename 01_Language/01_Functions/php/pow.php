<?php

var_dump(pow(2, 8)); // int(256)
echo pow(-1, 20), "\n"; // 1
echo pow(0, 0), "\n"; // 1
echo pow(10, -1), "\n"; // 0.1

echo pow(-1, 5.5), "\n"; // PHP >=5.2.2: NAN
echo pow(-1, 5.5), "\n"; // PHP <5.2.2: -NAN
