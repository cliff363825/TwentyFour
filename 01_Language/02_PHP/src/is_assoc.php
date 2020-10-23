<?php

function is_assoc($array) {
    foreach (array_keys($array) as $k => $v) {
        if ($k !== $v)
            return true;
    }
    return false;
}

$a = [1, 2, 3];
$b = ["a" => 1, "b" => 2, "c" => 3];

var_dump(is_assoc($a));
var_dump(is_assoc($b));
