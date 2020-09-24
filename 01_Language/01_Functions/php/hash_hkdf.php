<?php

// Generate a random key, and salt to strengthen it during derivation.
$inputKey = random_bytes(32);
$inputKey = "abcdef";
$salt = random_bytes(16);
$salt = "abcdef";

// Derive a pair of separate keys, using the same input created above.
$encryptionKey = hash_hkdf('sha256', $inputKey, 0, 'aes-256-encryption', $salt);
$authenticationKey = hash_hkdf('sha256', $inputKey, 0, 'sha-256-authentication', $salt);

var_dump($encryptionKey);
var_dump($authenticationKey);

var_dump($encryptionKey !== $authenticationKey); // bool(true)
