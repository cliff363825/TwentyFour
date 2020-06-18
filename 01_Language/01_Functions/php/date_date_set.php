<?php

$date = new DateTime();
$date->setDate(2001, 2, 3);
echo $date->format('Y-m-d');

$date = date_create();
date_date_set($date, 2001, 2, 3);
echo date_format($date, 'Y-m-d');
