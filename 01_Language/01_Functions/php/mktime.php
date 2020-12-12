<?php

// Set the default timezone to use. Available as of PHP 5.1
date_default_timezone_set('UTC');

echo mktime(0, 0, 0, 7, 1, 2000), "\n";

// Prints: July 1, 2000 is on a Saturday
echo "July 1, 2000 is on a " . date("l", mktime(0, 0, 0, 7, 1, 2000));

// Prints something like: 2006-04-05T01:02:03+00:00
echo date('c', mktime(1, 2, 3, 4, 5, 2006));
