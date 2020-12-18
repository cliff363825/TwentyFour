<?php

$binarydata = pack("nvc*", 0x1234, 0x5678, 65, 66);
for ($i = 0; $i < strlen($binarydata); $i++) {
    echo ord($binarydata[$i]) . "\n";
//    echo bindec($binarydata[$i]) . "\n";
}
