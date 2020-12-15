<?php

$array1 = $array2 = array('IMG0.png', 'img12.png', 'img10.png', 'img2.png', null, 'img1.png', 'IMG3.png');

sort($array1);
echo "Standard sorting\n";
print_r($array1);

natcasesort($array2);
echo "\nNatural order sorting (case-insensitive)\n";
print_r($array2);

$array3 = ['啊', '波', '吃', '爱', '笔', '虫'];
natcasesort($array3);
print_r($array3);
