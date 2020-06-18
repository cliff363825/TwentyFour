<?php

$list = array (
    array('aaa', 'bbb', 'ccc', 'dd,dd'),
    array('123', '456', '789'),
    array('"aaa"', '"bbb"')
);

$fp = fopen('test.csv', 'w');

foreach ($list as $fields) {
    fputcsv($fp, $fields);
}

fclose($fp);