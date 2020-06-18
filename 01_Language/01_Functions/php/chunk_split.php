<?php

// format $data using RFC 2045 semantics
$data = "Returns the chunked string.";
$new_string = chunk_split(base64_encode($data) . base64_encode($data) . base64_encode($data) . base64_encode($data) . base64_encode($data));
var_dump($new_string);
