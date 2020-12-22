<?php

$subject = array('1', 'a', '2', 'b', '3', 'A', 'B', '4');
$pattern = array('/\d/', '/[a-z]/', '/[1a]/');
$replace = array('A:$0', 'B:$0', 'C:$0');

echo "preg_filter returns\n";
print_r(preg_filter($pattern, $replace, $subject));

echo "preg_replace returns\n";
print_r(preg_replace($pattern, $replace, $subject));
