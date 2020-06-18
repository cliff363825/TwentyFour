<?php
// php < 5.3.2中没有提供ReflectionMethod::setAccessible，那怎么获取私有属性呢？
function getPrivatePropertyOfObj($obj, $name)
{
    $arr = (array)$obj;
    $key = "\0" . get_class($obj) . "\0" . $name;
    return isset($arr[$key]) ? $arr[$key] : null;
}

function getProtectedPropertyOfObj($obj, $name)
{
    $arr = (array)$obj;
    $key = "\0*\0" . $name;
    return isset($arr[$key]) ? $arr[$key] : null;
}

class Person
{
    private $_name;
    protected $_age;

    /**
     * Person constructor.
     * @param $_name
     * @param $_age
     */
    public function __construct($_name, $_age)
    {
        $this->_name = $_name;
        $this->_age = $_age;
    }
}

$p = new Person('呵呵', 16);
echo getPrivatePropertyOfObj($p, '_name');
echo getProtectedPropertyOfObj($p, '_age');
