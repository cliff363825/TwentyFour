<?php

$fp = fopen('test.txt', 'r');

// read some data
$data = fgets($fp, 4096);
var_dump($data);

// move back to the beginning of the file
// same as rewind($fp);
fseek($fp, 0);