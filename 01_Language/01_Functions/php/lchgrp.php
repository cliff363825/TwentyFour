<?php

$target = 'test.txt';
$link = 'text.html';
symlink($target, $link);

lchgrp($link, 8);
