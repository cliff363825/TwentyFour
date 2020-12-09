<?php

echo bin2hex(mhash(MHASH_MD5, 'admin')), "\n";
echo hash('md5', 'admin') . "\n";

echo bin2hex(mhash(MHASH_MD5, 'admin', '123')), "\n";


