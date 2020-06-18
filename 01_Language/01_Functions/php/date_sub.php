<?php

$date = new DateTime('2000-01-20');
$date->sub(new DateInterval('P10D'));
echo $date->format('Y-m-d') . "\n";

$date = date_create('2000-01-20');
date_sub($date, date_interval_create_from_date_string('10 days'));
echo date_format($date, 'Y-m-d');
