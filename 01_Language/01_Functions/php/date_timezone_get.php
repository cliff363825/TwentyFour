<?php

$date = new DateTime(null, new DateTimeZone('Europe/London'));
$tz = $date->getTimezone();
echo $tz->getName();

$date = date_create(null, timezone_open('Europe/London'));
$tz = date_timezone_get($date);
echo timezone_name_get($tz);
