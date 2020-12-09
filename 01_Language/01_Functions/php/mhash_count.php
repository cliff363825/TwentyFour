<?php

$nr = mhash_count();

for ($i = 0; $i <= $nr; $i++) {
    echo sprintf("The blocksize of %s is %d\n",
        mhash_get_hash_name($i),
        mhash_get_block_size($i));
}
