<?php

$url = 'http://www.onevgo.com';

print_r(get_headers($url));

print_r(get_headers($url, 1));
