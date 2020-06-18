<?php

// outputs e.g.  somefile.txt: 1024 bytes

$filename = 'test.txt';
echo $filename . ': ' . filesize($filename) . ' bytes';
