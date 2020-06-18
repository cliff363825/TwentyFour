<?php
// php中，`++$a` 和 `$a = $a + 1` 是一样的吗?
$a = '1d9';
echo ++$a . PHP_EOL; // 1e0
$b = '1d9';
$b = $b + 1;
echo $b . PHP_EOL; // 2
