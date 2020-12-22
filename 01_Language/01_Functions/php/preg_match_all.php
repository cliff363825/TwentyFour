<?php

preg_match_all("/\(?(\d{3})?\)?(?(1)[\-\s])\d{3}-\d{4}/",
    "Call 555-1212 or 1-800-555-1212", $phones);
var_dump($phones);
