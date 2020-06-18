<?php

if (!($fp = fopen('test.txt', 'w'))) {
    return;
}

$ymd = date("Y-m-d");
list($year, $month, $day) = explode("-", $ymd);
fprintf($fp, "%04d-%02d-%02d", $year, $month, $day);