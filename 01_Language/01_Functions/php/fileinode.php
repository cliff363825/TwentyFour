<?php

$filename = 'fileinode.php';
var_dump(fileinode($filename));
if (getmyinode() == fileinode($filename)) {
    echo 'You are checking the current file.';
}
