<?php
// php 中，`switch`是弱类型比较，可使用```swicth (true) { case $a === 'val1': break; }```
$a = 1;

switch (true) {

    case $a === true:
        echo 'hello strict case';
        break;

    default:
        echo 'I\'m default echo.';

}
