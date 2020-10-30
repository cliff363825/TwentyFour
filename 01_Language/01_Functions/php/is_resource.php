<?php

$fp = fopen("test.txt", "r");
if (!is_resource($fp)) {
    echo "fp is NOT resource";
}
