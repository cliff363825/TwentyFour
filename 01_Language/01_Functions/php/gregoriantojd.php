<?php

$jd = gregoriantojd(10, 11, 1970);
echo "$jd\n";
$gregorian = jdtogregorian($jd);
echo "$gregorian\n";

echo gregoriantojd(2, 31, 2018), PHP_EOL,
gregoriantojd(3,  3, 2018), PHP_EOL;
