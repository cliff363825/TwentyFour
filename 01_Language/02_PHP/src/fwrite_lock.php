<?php
// fopen以append模式打开文件时，fwrite本身就是原子操作(除非string大于系统的block size，一般为4k)，不需要使用flock
/*
If handle was fopen()ed in append mode,
fwrite()s are atomic (unless the size of string exceeds the filesystem's block size,
on some platforms, and as long as the file is on a local filesystem).
That is, there is no need to flock() a resource before calling fwrite();
all of the data will be written without interruption.
 */
$fd = fopen('test.txt', 'a+');
// flock($fd);
fwrite($fd, '测试内容');
fclose($fd);
