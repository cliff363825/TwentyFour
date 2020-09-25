<?php

echo hash_hmac('ripemd160', 'The quick brown fox jumped over the lazy dog.', 'secret');
