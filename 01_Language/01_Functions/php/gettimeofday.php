<?php

//ini_set('date.timezone', 'America/Whitehorse');
ini_set('date.timezone', 'Asia/Shanghai');

print_r(gettimeofday());

echo gettimeofday(true) . "\n";

echo date("Y-m-d H:i:s", gettimeofday()['sec']);
