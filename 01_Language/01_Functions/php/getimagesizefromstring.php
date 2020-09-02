<?php

$img = 'test.gif';

// Open as a file
$size_info1 = getimagesize($img);
var_dump($size_info1);

// Or open as a string
$data       = file_get_contents($img);
$size_info2 = getimagesizefromstring($data);
var_dump($size_info2);
