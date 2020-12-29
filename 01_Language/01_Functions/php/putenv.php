<?php

$uniqid = uniqid();
putenv("UNIQID=$uniqid");
print_r(getenv("UNIQID"));
print_r($_ENV);
