<?php

class myclass {
    // constructor
    function __construct()
    {
        return(true);
    }

    // method 1
    private function myfunc1()
    {
        return(true);
    }

    // method 2
    public function myfunc2()
    {
        return(true);
    }
}

$class_methods = get_class_methods('myclass');
// or
$class_methods = get_class_methods(new myclass());

foreach ($class_methods as $method_name) {
    echo "$method_name\n";
}
