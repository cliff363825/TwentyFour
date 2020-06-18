<?php

$date = new DateTime();
echo $date->getTimestamp();

echo "\n";

$date = date_create();
echo date_timestamp_get($date);
