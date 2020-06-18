<?php

interface foo { }
class bar implements foo {}

print_r(class_implements(new bar));

// since PHP 5.1.0 you may also specify the parameter as a string
print_r(class_implements('bar'));


function __autoload($class_name) {
    require_once $class_name . '.php';
}

// use __autoload to load the 'not_loaded' class
print_r(class_implements('not_loaded', true));
