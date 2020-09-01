<?php

$hostname = gethostbyaddr($_SERVER['REMOTE_ADDR']);
$hostname = gethostbyaddr("193.112.23.137");

echo $hostname;
