<?php

// prints: mysql link
$c = mysqli_connect('127.0.0.1', 'root', 'root', 'default', 3306);
echo get_resource_type($c) . "\n";

// prints: stream
$fp = fopen("test.txt", "r");
echo get_resource_type($fp) . "\n";

// prints: domxml document
//$doc = new_xmldoc("1.0");
//echo get_resource_type($doc->doc) . "\n";
