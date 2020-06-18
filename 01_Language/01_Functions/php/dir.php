<?php

$d = dir("./");
echo "Handle: " . $d->handle . "\n";
echo "Path: " . $d->path . "\n";
while (false !== ($entry = $d->read())) {
    echo $entry."\n";
}
$d->close();
