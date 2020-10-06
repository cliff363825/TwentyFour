<?php

/* setcookie() will add a response header on its own */
setcookie('foo', 'bar');

/* Define a custom response header
   This will be ignored by most clients */
header("X-Sample-Test: foo");

/* Specify plain text content in our response */
header('Content-type: text/plain');

/* What headers are going to be sent? */
var_dump(headers_list());
