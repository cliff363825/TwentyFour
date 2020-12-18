<?php

declare(encoding='UTF-8');
$str = "🐘";
for ( $pos=0; $pos < strlen($str); $pos ++ ) {
    $byte = substr($str, $pos);
    echo 'Byte ' . $pos . ' of $str has value ' . ord($byte) . PHP_EOL;
}
