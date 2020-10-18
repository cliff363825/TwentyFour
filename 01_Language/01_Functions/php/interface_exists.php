<?php

// Check the interface exists before trying to use it
if (interface_exists('MyInterface')) {
    class MyClass implements MyInterface
    {
        // Methods
    }
}
