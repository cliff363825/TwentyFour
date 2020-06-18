<?php

$date = new DateTime('2000-01-01');
echo $date->format('Y-m-d H:i:s');

$date = date_create('2000-01-01');
echo date_format($date, 'Y-m-d H:i:s');
