<?php

ini_set("date.timezone", "Asia/Shanghai");
echo date("M d Y H:i:s", mktime(0, 0, 0, 1, 1, 1998)) . "\n";
echo gmdate("M d Y H:i:s", mktime(0, 0, 0, 1, 1, 1998));

echo "--------\n";

echo date("Y-m-d H:i:s") . "\n";
echo gmdate("Y-m-d H:i:s") . "\n";
