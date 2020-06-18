<?php
$input_array = array("FirSt" => 1, "SecOnd" => 4);
var_dump(array_change_key_case($input_array, CASE_LOWER));

//array(2) {
//  ["FIRST"]=>
//  int(1)
//  ["SECOND"]=>
//  int(4)
//}
