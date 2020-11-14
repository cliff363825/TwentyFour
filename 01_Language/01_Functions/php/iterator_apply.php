<?php

function print_caps(Iterator $iterator) {
    echo strtoupper($iterator->current()) . "\n";
    return TRUE;
}

$it = new ArrayIterator(array("Apples", "Bananas", "Cherries"));
$n  = iterator_apply($it, "print_caps", array($it));
var_dump($n, $it);
