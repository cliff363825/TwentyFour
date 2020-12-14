<?php

function randomFloat($min = 0, $max = 1) {
    return $min + mt_rand() / mt_getrandmax() * ($max - $min);
}

var_dump(randomFloat());
var_dump(randomFloat(2, 20));
