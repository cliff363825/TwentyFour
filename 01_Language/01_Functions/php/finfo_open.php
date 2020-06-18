<?php

//$finfo = finfo_open(FILEINFO_MIME, "/usr/share/misc/magic"); // return mime type ala mimetype extension
$finfo = finfo_open(FILEINFO_MIME); // return mime type ala mimetype extension

if (!$finfo) {
    echo "Opening fileinfo database failed";
    exit();
}

/* get mime-type for a specific file */
$filename = "test.txt";
echo finfo_file($finfo, $filename);

/* close connection */
finfo_close($finfo);
