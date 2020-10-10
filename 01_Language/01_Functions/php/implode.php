<?php

$array = array('lastname', 'email', 'phone');
$comma_separated = implode(",", $array);

echo $comma_separated; // lastname,email,phone
echo "\n";

// Empty string when using an empty array:
var_dump(implode('hello', array())); // string(0) ""
