<?php

// Example use of getenv()
$ip = getenv('REMOTE_ADDR');

// Or simply use a Superglobal ($_SERVER or $_ENV)
$ip = $_SERVER['REMOTE_ADDR'];

// Safely get the value of an environment variable, ignoring whether
// or not it was set by a SAPI or has been changed with putenv
$ip = getenv('REMOTE_ADDR', true) ?: getenv('REMOTE_ADDR');

var_dump(getenv());
