<?php
// php中，浮点数计算的精度问题
var_dump(intval((0.7 + 0.1) * 10)); // 7
var_dump(intval((0.7 + 0.1) * 100 / 10)); // 8
var_dump(intval((0.8 * 10) . '')); // 8
var_dump(intval(bcmul(0.8, 10))); // 8
