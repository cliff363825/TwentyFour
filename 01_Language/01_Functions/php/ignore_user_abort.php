<?php

// Ignore user aborts and allow the script
// to run forever
ignore_user_abort(true);
set_time_limit(0);

echo 'Testing connection handling in PHP';

// Run a pointless loop that sometime
// hopefully will make us click away from
// page or click the "Stop" button.
while(1)
{
    // Did the connection fail?
    if(connection_status() != CONNECTION_NORMAL)
    {
        break;
    }

    // Sleep for 10 seconds
    sleep(10);
}

// If this is reached, then the 'break'
// was triggered from inside the while loop

// So here we can log, or perform any other tasks
// we need without actually being dependent on the
// browser.
