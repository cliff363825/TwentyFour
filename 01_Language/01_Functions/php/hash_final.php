<?php

$ctx = hash_init('sha1');
hash_update($ctx, 'The quick brown fox jumped over the lazy dog.');
echo hash_final($ctx);
