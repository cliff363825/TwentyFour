<?php

$dir = "./";
var_dump('ls '.escapeshellarg($dir));

$dir = "','";
var_dump('ls '.escapeshellarg($dir));
