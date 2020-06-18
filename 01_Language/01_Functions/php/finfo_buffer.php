<?php

$finfo = new finfo(FILEINFO_MIME);
echo $finfo->buffer(file_get_contents("test.gif")) . "\n";
