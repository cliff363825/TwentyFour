<?php

//get 3 commands from user
for ($i=0; $i < 3; $i++) {
    $line = readline("Command: ");
    readline_add_history($line);
}

//dump history
if (function_exists('readline_list_history')) {
    print_r(readline_list_history());
    // ...
} else {
    echo 'Not supported by the compiled library.'.PHP_EOL;
}

//dump variables
print_r(readline_info());
