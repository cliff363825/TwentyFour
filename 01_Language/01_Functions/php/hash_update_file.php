<?php

$ctx = hash_init('md5');
file_put_contents("test.txt", "The quick brown fox jumped over the lazy dog.");
hash_update_file($ctx, "test.txt");
echo hash_final($ctx);
