<?php

// chop() = rtrim()
$text = "\t\tThese are a few words :) ...  ";
$binary = "\x09Example string\x0A";
$hello  = "Hello World";
var_dump($text, $binary, $hello);

print "\n";

$trimmed = chop($text);
var_dump($trimmed);

$trimmed = chop($text, " \t.");
var_dump($trimmed);

$trimmed = chop($hello, "Hdle");
var_dump($trimmed);

// trim the ASCII control characters at the end of $binary
// (from 0 to 31 inclusive)
$clean = chop($binary, "\x00..\x1F");
var_dump($clean);
