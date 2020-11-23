<?php

$foo = 'HelloWorld';
$foo = lcfirst($foo);             // helloWorld
var_dump($foo);

$bar = 'HELLO WORLD!';
$bar = lcfirst($bar);             // hELLO WORLD!
$bar = lcfirst(strtoupper($bar)); // hELLO WORLD!
var_dump($bar);

var_dump(lcfirst(" Abc"));
var_dump(lcfirst(null));
