<?php

// define a class
class WidgetFactory
{
    var $oink = 'moo';
}

// define a child class
class WidgetFactory_Child extends WidgetFactory
{
    var $oink = 'oink';
}

// create a new object
$WF = new WidgetFactory();
$WFC = new WidgetFactory_Child();

if (is_subclass_of($WFC, 'WidgetFactory')) {
    echo "yes, \$WFC is a subclass of WidgetFactory\n";
} else {
    echo "no, \$WFC is not a subclass of WidgetFactory\n";
}


if (is_subclass_of($WF, 'WidgetFactory')) {
    echo "yes, \$WF is a subclass of WidgetFactory\n";
} else {
    echo "no, \$WF is not a subclass of WidgetFactory\n";
}


// usable only since PHP 5.0.3
if (is_subclass_of('WidgetFactory_Child', 'WidgetFactory')) {
    echo "yes, WidgetFactory_Child is a subclass of WidgetFactory\n";
} else {
    echo "no, WidgetFactory_Child is not a subclass of WidgetFactory\n";
}

if (is_subclass_of(null, 'WidgetFactory')) {
    echo "yes, null is a subclass of WidgetFactory\n";
} else {
    echo "no, null is not a subclass of WidgetFactory\n";
}

// Define the Interface
interface MyInterface
{
    public function MyFunction();
}

// Define the class implementation of the interface
class MyClass implements MyInterface
{
    public function MyFunction()
    {
        return "MyClass Implements MyInterface!";
    }
}

// Instantiate the object
$my_object = new MyClass;

// Works since 5.3.7

// Test using the object instance of the class
if (is_subclass_of($my_object, 'MyInterface')) {
    echo "Yes, \$my_object is a subclass of MyInterface\n";
} else {
    echo "No, \$my_object is not a subclass of MyInterface\n";
}

// Test using a string of the class name
if (is_subclass_of('MyClass', 'MyInterface')) {
    echo "Yes, MyClass is a subclass of MyInterface\n";
} else {
    echo "No, MyClass is not a subclass of MyInterface\n";
}
