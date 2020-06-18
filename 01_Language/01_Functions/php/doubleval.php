<?php

$var = '122.34343The';
$float_value_of_var = floatval($var);
echo $float_value_of_var; // 122.34343

echo "\n";
$var = '+122.34343.1';
$float_value_of_var = floatval($var);
echo $float_value_of_var; // 122.34343

echo "\n";
$var = '-122.34343.1';
$float_value_of_var = floatval($var);
echo $float_value_of_var; // 122.34343

echo "\n";
$var = '-.34343.1';
$float_value_of_var = floatval($var);
echo $float_value_of_var; // 122.34343

echo "\n";
$var = 'The122.34343';
$float_value_of_var = floatval($var);
echo $float_value_of_var; // 0
