<?php
$input = array("Neo", "Morpheus", "Trinity", "Cypher", "Tank");
$rand_keys = array_rand($input, 2);
var_dump($rand_keys);

$rand_keys = array_rand($input);
var_dump($rand_keys);

$input = array("k1" => "Neo", "k2" => "Morpheus", "k3" => "Trinity", "k4" => "Cypher", "k5" => "Tank");
$rand_keys = array_rand($input, 2);
var_dump($rand_keys);
