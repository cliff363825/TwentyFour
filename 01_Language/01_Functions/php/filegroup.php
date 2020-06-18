<?php

$filename = 'test.txt';
print_r(filegroup($filename));
print_r(posix_getgrgid(filegroup($filename)));
