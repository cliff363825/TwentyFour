<?php

// File name and username to use
$path = "test.txt";
$user_name = "root";

// Set the user
chown($path, $user_name);

// Check the result
$stat = stat($path);
print_r(posix_getpwuid($stat['uid']));
