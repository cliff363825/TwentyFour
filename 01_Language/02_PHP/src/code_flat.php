<?php

// php 中，逻辑判断嵌套比较多的时候，可以巧用```do { break; } while(0)```来扁平化你的代码

do {
    # hello
    if (false) break;
    # hi
    if (true) break;
} while (0);
