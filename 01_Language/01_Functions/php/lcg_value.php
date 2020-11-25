<?php

function mt_randf($min, $max)
{
    return $min + abs($max - $min) * mt_rand(0, mt_getrandmax())/mt_getrandmax();
}
function lcg_randf($min, $max)
{
    return $min + lcg_value() * abs($max - $min);
}
function randf($min, $max)
{
    return $min + rand(0,getrandmax()) / getrandmax() * abs($max - $min);
}

var_dump(lcg_randf(1, 2));
