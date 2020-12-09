<?php

// This is only an example, the numbers below will
// differ depending on your system

echo memory_get_usage() . "\n"; // 36640

$a = str_repeat("Hello", 4242);

echo memory_get_usage() . "\n"; // 57960

unset($a);

echo memory_get_usage() . "\n"; // 36744
