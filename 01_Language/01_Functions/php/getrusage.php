<?php

$dat = getrusage();
var_dump($dat);
echo $dat["ru_oublock"] . "\n";       // number of block output operations
echo $dat["ru_inblock"] . "\n";       // number of block input operations
echo $dat["ru_msgsnd"] . "\n";        // number of IPC messages sent
echo $dat["ru_msgrcv"] . "\n";        // number of IPC messages received
echo $dat["ru_maxrss"] . "\n";        // maximum resident set size
echo $dat["ru_ixrss"] . "\n";         // integral shared memory size
echo $dat["ru_idrss"] . "\n";         // integral unshared data size
echo $dat["ru_minflt"] . "\n";        // number of page reclaims (soft page faults)
echo $dat["ru_majflt"] . "\n";        // number of page faults (hard page faults)
echo $dat["ru_nsignals"] . "\n";      // number of signals received
echo $dat["ru_nvcsw"] . "\n";         // number of voluntary context switches
echo $dat["ru_nivcsw"] . "\n";        // number of involuntary context switches
echo $dat["ru_nswap"] . "\n";         // number of swaps
echo $dat["ru_utime.tv_usec"] . "\n"; // user time used (microseconds)
echo $dat["ru_utime.tv_sec"] . "\n";  // user time used (seconds)
echo $dat["ru_stime.tv_usec"] . "\n"; // system time used (microseconds)
