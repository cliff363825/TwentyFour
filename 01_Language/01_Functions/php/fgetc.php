<?php

$fp = fopen('test.txt', 'r');
if (!$fp) {
    echo 'Could not open file test.txt';
}
while (false !== ($char = fgetc($fp))) {
    echo "$char\n";
}
