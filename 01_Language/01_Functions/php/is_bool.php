<?php

$a = false;
$b = 0;

// Since $a is a boolean, it will return true
if (is_bool($a) === true) {
    echo "Yes, this is a boolean";
}

// Since $b is not a boolean, it will return false
if (is_bool($b) === false) {
    echo "No, this is not a boolean";
}
