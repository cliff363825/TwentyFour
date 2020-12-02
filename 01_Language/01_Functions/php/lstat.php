<?php

symlink('test.txt', 'test');

// Contrast information for uploads.php and uploads
var_dump(lstat('test'));
var_dump(array_diff(stat('test.txt'), lstat('test')));
