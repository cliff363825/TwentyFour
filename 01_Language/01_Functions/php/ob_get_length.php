<?php

ob_start();

echo "Hello ";

$len1 = ob_get_length();

echo "World";

$len2 = ob_get_length();

ob_end_clean();

echo $len1 . ", " . $len2;
