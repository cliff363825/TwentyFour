<?php

$ip   = gethostbyname('www.onevgo.com');
$long = ip2long($ip);

if ($long == -1 || $long === FALSE) {
    echo 'Invalid IP, please try again';
} else {
    echo $ip   . "\n";            // 192.0.34.166
    echo $long . "\n";            // 3221234342 (-1073732954 on 32-bit systems, due to integer overflow)
    printf("%u\n", ip2long($ip)); // 3221234342
}


$long = ip2long("999.999.999.999");
var_dump($long);
