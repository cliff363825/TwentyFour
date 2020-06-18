<?php

$date = new DateTime('2006-12-12');
$date->modify('+1 day');
echo $date->format('Y-m-d');

$date = date_create('2006-12-12');
date_modify($date, '+1 day');
echo date_format($date, 'Y-m-d');
