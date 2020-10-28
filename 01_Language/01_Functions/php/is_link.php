<?php

$link = 'uploads';

if (is_link($link)) {
    echo(readlink($link));
} else {
    symlink('uploads.php', $link);
}
