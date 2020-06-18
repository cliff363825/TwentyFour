<?php

$hashed_password = crypt('mypassword'); // let the salt be automatically generated
var_dump($hashed_password);

//$user_input = "123456";
$user_input = "mypassword";
/* You should pass the entire results of crypt() as the salt for comparing a
   password, to avoid problems when different hashing algorithms are used. (As
   it says above, standard DES-based password hashing uses a 2-character salt,
   but MD5-based hashing uses 12.) */
if (hash_equals($hashed_password, crypt($user_input, $hashed_password))) {
    echo "Password verified!";
}
