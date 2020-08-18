<?php

$fp = tmpfile();
var_dump(get_resources());
var_dump(get_resources('stream'));
var_dump(get_resources('curl'));
