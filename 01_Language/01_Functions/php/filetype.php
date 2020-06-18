<?php

// find ./ -type [char|dir|block|link|file|socket]
echo filetype('/dev/urandom') . "\n";       // char
echo filetype('/dev/') . "\n";              // dir
echo filetype('/dev/disk0') . "\n";         // block
echo filetype('/dev/stdin') . "\n";         // link
echo filetype('/etc/passwd') . "\n";        // file
echo filetype('/var/run/syslog') . "\n";    // socket
