<?php

// This file is abc.php

include 'abs.php';
include_once 'acos.php';
require 'acosh.php';
require_once 'addslashes.php';

$included_files = get_included_files();

foreach ($included_files as $filename) {
    echo "$filename\n";
}
