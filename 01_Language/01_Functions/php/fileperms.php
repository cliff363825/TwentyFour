<?php

var_dump(fileperms('test.txt'));
echo substr(sprintf('%o', fileperms('test.txt')), -4);
