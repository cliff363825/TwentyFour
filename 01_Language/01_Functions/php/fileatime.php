<?php

// outputs e.g.  somefile.txt was last accessed: December 29 2002 22:16:23.

$filename = 'test.txt';
if (file_exists($filename)) {
    echo "$filename was last accessed: " . date("F d Y H:i:s.", fileatime($filename));
}
