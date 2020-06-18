<?php

$filename = 'test.txt';

$handle = fopen($filename, 'r+');
ftruncate($handle, 10);
rewind($handle);
echo fread($handle, filesize($filename));
fclose($handle);