<?php
// php 中，echo 和 print 都是语言结构而不是函数，所以最好不要使用函数的调用习惯

echo '1' . print(2) + 3;
