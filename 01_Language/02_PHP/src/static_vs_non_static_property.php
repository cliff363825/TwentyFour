<?php

/**
 * Class A
 * @property array $type
 */
class A {
    private $_attributes = [
        'type' => [
            3 => 'C',
        ],
    ];
    /**
     * @var array
     */
    public static $type = [
        1 => 'A',
        2 => 'B',
    ];

    public function __get($name)
    {
        if (isset($this->_attributes[$name])) {
            return $this->_attributes[$name];
        }
        throw new \Exception("Attribute $name not found.");
    }
}

var_dump(A::$type);
$a = new A();
var_dump($a->type);
