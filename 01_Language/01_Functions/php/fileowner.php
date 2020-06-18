<?php

$filename = 'test.txt';
var_dump(fileowner($filename));
print_r(posix_getpwuid(fileowner($filename)));
