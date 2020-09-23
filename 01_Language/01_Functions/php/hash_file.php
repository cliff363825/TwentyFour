<?php

/* Create a file to calculate hash of */
file_put_contents('test.txt', 'The quick brown fox jumped over the lazy dog.');

echo hash_file('md5', 'test.txt');
