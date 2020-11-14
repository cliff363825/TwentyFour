<?php

$filename = 'test.txt';
if (is_writeable($filename)) {
    echo 'The file is writable';
} else {
    echo 'The file is not writable';
}
