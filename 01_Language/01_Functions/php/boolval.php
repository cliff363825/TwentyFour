<?php

echo '0:        ' . (boolval(0) ? 'true' : 'false') . "\n";
echo '42:       ' . (boolval(42) ? 'true' : 'false') . "\n";
echo '0.0:      ' . (boolval(0.0) ? 'true' : 'false') . "\n";
echo '4.2:      ' . (boolval(4.2) ? 'true' : 'false') . "\n";
echo '"":       ' . (boolval("") ? 'true' : 'false') . "\n";
echo '" ":      ' . (boolval(" ") ? 'true' : 'false') . "\n";
echo '"string": ' . (boolval("string") ? 'true' : 'false') . "\n";
echo '"true":   ' . (boolval("true") ? 'true' : 'false') . "\n";
echo '"false":  ' . (boolval("false") ? 'true' : 'false') . "\n";
echo '"0":      ' . (boolval("0") ? 'true' : 'false') . "\n";
echo '"1":      ' . (boolval("1") ? 'true' : 'false') . "\n";
echo '[1, 2]:   ' . (boolval([1, 2]) ? 'true' : 'false') . "\n";
echo '[]:       ' . (boolval([]) ? 'true' : 'false') . "\n";
echo 'stdClass: ' . (boolval(new stdClass) ? 'true' : 'false') . "\n";
