<?php

var_dump(gc_enabled());
ini_set('zend.enable_gc', 0);
var_dump(gc_enabled());
