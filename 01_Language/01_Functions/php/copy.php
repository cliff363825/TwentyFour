<?php

$file = 'test.txt';
$newfile = 'test.txt.bak';

if (!copy($file, $newfile)) {
    echo "failed to copy $file...\n";
}
