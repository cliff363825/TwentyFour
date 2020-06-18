<?php
// php中,sleep是不支持浮点的，zend_parse_parameters(ZEND_NUM_ARGS() TSRMLS_CC, "l", &num)；在分析参数的时候就会转换成 int
echo microtime(true) . "\n";
sleep(0.01); // sleep(0);
echo microtime(true) . "\n";
usleep(1000000);
echo microtime(true) . "\n";
