<?php

// Assumes the string will be used as ASCII or an ASCII-compatible encoding

$str = "The string ends in escape: ";
$str .= chr(27); /* add an escape character at the end of $str */
var_dump($str);

/* Often this is more useful */

$str = sprintf("The string ends in escape: %c", 27);
var_dump($str);

$str = chr(240) . chr(159) . chr(144) . chr(152);
echo $str;
