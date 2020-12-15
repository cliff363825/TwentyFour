<?php

$transport = array('foot', 'bike', 'car', 'plane');
$mode = current($transport); // $mode = 'foot';
echo $mode, "\n";
$mode = next($transport);    // $mode = 'bike';
echo $mode, "\n";
$mode = next($transport);    // $mode = 'car';
echo $mode, "\n";
$mode = prev($transport);    // $mode = 'bike';
echo $mode, "\n";
$mode = end($transport);     // $mode = 'plane';
echo $mode, "\n";
