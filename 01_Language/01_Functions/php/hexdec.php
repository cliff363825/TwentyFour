<?php

var_dump(hexdec("See"));
var_dump(hexdec("ee"));
// both print "int(238)"

var_dump(hexdec("that")); // print "int(10)"
var_dump(hexdec("thatthat")); // print "int(10)"
var_dump(hexdec("that.that")); // print "int(10)"
var_dump(hexdec("a0")); // print "int(160)"
