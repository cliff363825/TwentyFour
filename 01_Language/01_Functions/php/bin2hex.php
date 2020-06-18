<?php

$bytes = pack("H*", base_convert("1100001", 2, 16));
echo $bytes . "\n"; // a
echo bin2hex($bytes) . "\n"; // 61

$bytes = pack("a*", "中国");
echo $bytes . "\n"; // 中国
echo bin2hex($bytes) . "\n"; // e4b8ade59bbd
echo hex2bin("e4b8ade59bbd");
