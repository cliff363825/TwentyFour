<?php

$path_parts = pathinfo('./test.txt');

var_dump($path_parts);
//echo $path_parts['dirname'], "\n";
//echo $path_parts['basename'], "\n";
//echo $path_parts['extension'], "\n";
//echo $path_parts['filename'], "\n"; // since PHP 5.2.0
