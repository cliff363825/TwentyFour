<?php

//using output_buffering=On
print_r(ob_list_handlers());
ob_end_flush();

ob_start("ob_gzhandler");
print_r(ob_list_handlers());
ob_end_flush();

// anonymous functions
ob_start(function($string) { return $string; });
print_r(ob_list_handlers());
ob_end_flush();
