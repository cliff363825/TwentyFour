<?php

$in_addr = inet_pton('127.0.0.1');
var_dump($in_addr);

$in6_addr = inet_pton('::1');
var_dump($in6_addr);
