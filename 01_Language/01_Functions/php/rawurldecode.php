<?php

echo rawurldecode('foo%20bar%40baz'), "\n"; // foo bar@baz
echo rawurldecode('foo+bar%40baz'); // foo+bar@baz
