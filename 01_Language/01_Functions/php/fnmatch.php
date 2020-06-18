<?php

$colors = ["agray", "egrey", "aegraey"];
foreach ($colors as $color) {

    if (fnmatch("*gr[ae]y", $color)) {
        echo "$color => some form of gray ...\n";
    }
}
