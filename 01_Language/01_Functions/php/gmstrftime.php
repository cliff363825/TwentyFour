<?php

//setlocale(LC_TIME, 'en_US');
ini_set("date.timezone", "Asia/Shanghai");

echo strftime("%b %d %Y %H:%M:%S", mktime(20, 0, 0, 12, 31, 98)) . "\n";
echo gmstrftime("%b %d %Y %H:%M:%S", mktime(20, 0, 0, 12, 31, 98)) . "\n";
