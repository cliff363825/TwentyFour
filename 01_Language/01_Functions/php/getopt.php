<?php

// INPUT: php getopt.php -a 1 -b=2 -c --required 3 --optional=4 --option
// OUTPUT:
// array(6) {
//  ["a"]=>
//  string(1) "1"
//  ["b"]=>
//  string(1) "2"
//  ["c"]=>
//  bool(false)
//  ["required"]=>
//  string(1) "3"
//  ["optional"]=>
//  string(1) "4"
//  ["option"]=>
//  bool(false)
//}
$options = getopt("a:b::cd", ["required:", "optional::", "option", "opt"]);
var_dump($options);
