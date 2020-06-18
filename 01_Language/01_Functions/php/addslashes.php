<?php
$str = "' \" \\ \0";

var_dump($str, addslashes($str));
