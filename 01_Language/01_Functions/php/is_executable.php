<?php

$file = './is_double.php';

if (is_executable($file)) {
    echo $file.' is executable';
} else {
    echo $file.' is not executable';
}
