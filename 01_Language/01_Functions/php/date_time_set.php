<?php

$date = new DateTime('2001-01-01');

$date->setTime(14, 55);
echo $date->format('Y-m-d H:i:s') . "\n";

$date->setTime(14, 55, 24);
echo $date->format('Y-m-d H:i:s') . "\n";

$date = date_create('2001-01-01');

date_time_set($date, 14, 55);
echo date_format($date, 'Y-m-d H:i:s') . "\n";

date_time_set($date, 14, 55, 24);
echo date_format($date, 'Y-m-d H:i:s') . "\n";
