<?php

$checksum = crc32("The quick brown fox jumped over the lazy dog.");
// for 32bits os
printf("%u\n", $checksum);
