<?php
// php 中，php>5.3，三木运算可省略第一个参数，且默认为条件的值
// Since PHP 5.3, it is possible to leave out the middle part of the ternary operator.
// Expression expr1 ?: expr3 returns expr1 if expr1 evaluates to TRUE, and expr3 otherwise.
$a = array('hi');

$a = $a ?: array('hello');
