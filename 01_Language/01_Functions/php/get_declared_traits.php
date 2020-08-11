<?php

namespace Example;

// Declare Trait
trait FooTrait
{
}

// Declare Abstract class
abstract class FooAbstract
{
}

// Declare class
class Bar extends FooAbstract
{
    use FooTrait;
}

// Get all traits declareds
$array = get_declared_traits();

var_dump($array);
/**
 * Result:

 * array(1) {
 *  [0] =>
 *  string(23) "Example\FooTrait"
 * }
 */
