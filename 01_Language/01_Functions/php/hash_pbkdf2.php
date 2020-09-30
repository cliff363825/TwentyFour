<?php

$password = "password";
$iterations = 1000;

// Generate a random IV using openssl_random_pseudo_bytes()
// random_bytes() or another suitable source of randomness
//$salt = openssl_random_pseudo_bytes(16);
//echo $salt . "\n";
$salt = "abcdef";

$hash = hash_pbkdf2("sha256", $password, $salt, $iterations);
echo $hash . "\n";

$hash = hash_pbkdf2("sha256", $password, $salt, $iterations, 20);
echo $hash;
