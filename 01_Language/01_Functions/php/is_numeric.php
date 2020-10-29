<?php

$tests = array(
    "42",
    1337,
    0x539,
    02471,
    0b10100111001,
    1337e0,
    "0x539",
    "02471",
    " 02471",
    "02471 ",
    "02 471",
    "0b10100111001",
    "1337e0",
    "e0",
    "0e",
    "not numeric",
    array(),
    9.1,
    "9.1",
    "9.",
    ".1",
    null
);

foreach ($tests as $element) {
    if (is_numeric($element)) {
        echo var_export($element, true) . " is numeric", PHP_EOL;
    } else {
        echo var_export($element, true) . " is NOT numeric", PHP_EOL;
    }
}
