<?php

$search_array = array('first' => 1, 'second' => 4, '' => false);
if (key_exists('first', $search_array)) {
    echo "The 'first' element is in the array";
}

if (key_exists(0, $search_array)) {
    echo "The '0' element is in the array";
}
