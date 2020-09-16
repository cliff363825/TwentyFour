<?php

// Prints: July 1, 2000 is on a Saturday
//echo "July 1, 2000 is on a " . date("l", gmmktime(0, 0, 0, 7, 1, 2000));
ini_set("date.timezone", "Asia/Shanghai");
echo gmmktime(0, 0, 0, 7, 1, 2000) . "\n";
echo mktime(0, 0, 0, 7, 1, 2000) . "\n";

echo date('Y-m-d H:i:s', gmmktime(0, 0, 0, 7, 1, 2000)) . "\n";
echo date('Y-m-d H:i:s', mktime(0, 0, 0, 7, 1, 2000)) . "\n";
