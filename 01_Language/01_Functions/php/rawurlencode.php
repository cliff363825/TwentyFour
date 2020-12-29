<?php

echo rawurlencode('foo bar@baz'), "\n";
echo rawurlencode('foo+bar@baz'), "\n";
echo rawurlencode('+*~');
