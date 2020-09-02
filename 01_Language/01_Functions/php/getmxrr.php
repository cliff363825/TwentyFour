<?php

$mxhosts = [];
var_dump(getmxrr("exmail.qq.com", $mxhosts));
var_dump($mxhosts);
