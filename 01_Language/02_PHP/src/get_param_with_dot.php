<?php
// http://example.com/?a.b=1 怎么获取a.b的值？
/*
https://www.php.net/manual/en/language.variables.external.php#language.variables.external.dot-in-names

The full list of field-name characters that PHP converts to _ (underscore) is the following (not just dot):
chr(32) ( ) (space)
chr(46) (.) (dot)
chr(91) ([) (open square bracket)
chr(128) - chr(159) (various)

PHP irreversibly modifies field names containing these characters in an attempt to maintain compatibility with the deprecated register_globals feature.
 */
echo $_GET['a.b']; // 这样？不对

echo $_GET['a_b']; // 这样就对了
