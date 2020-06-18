<?php

// 2019-03-31 => -1 month => 2019-03-03, expect 2019-02-28
echo "2019-03-31 => -1 month => " . date("Y-m-d", strtotime("-1 month", strtotime("2019-03-31"))) . "\n";
echo "2019-03-31 => last month => " . date("Y-m-d", strtotime("last month", strtotime("2019-03-31"))) . "\n";

// 2019-01-31 => +1 month => 2019-03-03, expect 2019-02-28
echo "2019-01-31 => +1 month => " . date("Y-m-d", strtotime("+1 month", strtotime("2019-01-31"))) . "\n";
echo "2019-01-31 => next month => " . date("Y-m-d", strtotime("next month", strtotime("2019-01-31"))) . "\n";

echo "-------------\n";

// 2019-03-31 => last day of -1 month => 2019-02-28
echo "2019-03-31 => last day of -1 month => " . date("Y-m-d", strtotime("last day of -1 month", strtotime("2019-03-31"))) . "\n";
echo "2019-03-31 => last day of last month => " . date("Y-m-d", strtotime("last day of last month", strtotime("2019-03-31"))) . "\n";

// 2019-01-31 => last day of +1 month => 2019-02-28
echo "2019-01-31 => last day of +1 month => " . date("Y-m-d", strtotime("last day of +1 month", strtotime("2019-01-31"))) . "\n";
echo "2019-01-31 => last day of next month => " . date("Y-m-d", strtotime("last day of next month", strtotime("2019-01-31"))) . "\n";
