<?php

//using output_buffering=On
print_r(ob_list_handlers());

//save buffer in a file
$buffer = ob_get_flush();
file_put_contents('test_buffer.txt', $buffer);

print_r(ob_list_handlers());
