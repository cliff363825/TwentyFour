<?php

foreach (glob("*.php") as $filename) {
    echo "$filename size " . filesize($filename) . "\n";
}
