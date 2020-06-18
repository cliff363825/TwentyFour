<?php

// $df contains the number of bytes available on "/"
$df = disk_free_space("/");
var_dump($df);

// On Windows:
//$df_c = disk_free_space("C:");
//$df_d = disk_free_space("D:");
