<?php

// opens a file and read some data
$fp = fopen("test.txt", "r");
$data = fgets($fp, 12);
echo $data . "\n";

// where are we ?
echo ftell($fp); // 11

fclose($fp);
