<?php

//ini_set('date.timezone', 'America/Whitehorse');

print_r(gettimeofday());

echo gettimeofday(true) . "\n";

echo date("Y-m-d H:i:s", gettimeofday()['sec']);
