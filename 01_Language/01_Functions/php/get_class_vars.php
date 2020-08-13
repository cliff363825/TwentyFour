<?php

class TestCase
{
    public $a    = 1;
    protected $b = 2;
    private $c   = 3;

    public static function expose()
    {
        var_dump(get_class_vars(__CLASS__));
    }
}

TestCase::expose();
echo "=================\n";
var_dump(get_class_vars('TestCase'));
