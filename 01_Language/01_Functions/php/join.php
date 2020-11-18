<?php

$array = array('lastname', 'email', 'phone');
$comma_separated = join(",", $array);

echo $comma_separated; // lastname,email,phone

// Empty string when using an empty array:
var_dump(join('hello', array())); // string(0) ""
