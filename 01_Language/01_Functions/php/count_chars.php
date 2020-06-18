<?php

$data = "Two Ts and one F.";

var_dump(count_chars($data, 3));
var_dump(count_chars($data, 4));
foreach (count_chars($data, 1) as $i => $val) {
    echo "[{$i}]: There were $val instance(s) of \"" , chr($i) , "\" in the string.\n";
}
