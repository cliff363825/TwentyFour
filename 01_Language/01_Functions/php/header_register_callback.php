<?php

header('Content-Type: text/plain');
header('X-Test: foo');

function foo() {
    file_put_contents(__DIR__ . "/test.txt", json_encode(headers_list(), true) . "\n", FILE_APPEND);
    foreach (headers_list() as $header) {
        if (strpos($header, 'X-Powered-By:') !== false) {
            header_remove('X-Powered-By');
        }
        header_remove('X-Test');
    }
}

$result = header_register_callback('foo');
echo "a";
