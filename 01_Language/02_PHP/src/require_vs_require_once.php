<?php
// PHP中require和require_once的区别，除了别人告诉你的可以引入一次和多次的不同，require_once第二次会返回true
$config = require_once __DIR__ . '/require_config.php';
var_dump($config); // ["config" => ""]
$config = require_once __DIR__ . '/require_config.php';
var_dump($config); // true
