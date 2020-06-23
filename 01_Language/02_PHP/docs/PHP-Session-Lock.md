# PHP Session Locking How To Prevent Sessions Blocking in PHP requests

What I've found when talking about PHP session locking is that half my conversations revolve around "*oh I remember PHP session blocking, it cost me a couple of days of my life but seriously improved my applications' performance once I avoided them*" or the other end "*what the heck is PHP session blocking?*".

Specifically, the locking mechanisms involved in PHP sessions aren’t clear to everyone and can cause slow applications if you don’t take them into account.

It doesn’t have to be a problem though, if you’re aware of what goes on behind the scenes you can anticipate this behaviour in your PHP applications and avoid it altogether.

## What happens when you call session_start()

Let’s take a basic PHP configuration as an example: whenever you start a PHP session, PHP will create a flat file in the `session.save_path` path, this defaults to `/var/lib/php/session`. All session data is stored there.

If your user didn’t have a session cookie yet, a new ID will be generated and the user will get a cookie set on his machine. If it’s a returning user, he’ll send his cookie ID to your webserver, PHP will parse it and will load the corresponding session data from `session.save_path`.

That’s `session_start()` in a nutshell.

## Session locks and concurrency

In a slightly more complete example: here’s what happens behind the scenes when a session is initiated in PHP.

| Timing | PHP code                                         | Linux / Server                                               |
| ------ | ------------------------------------------------ | ------------------------------------------------------------ |
| 0ms    | session_start();                                 | File lock created on /var/lib/php/session/sess_$identifier   |
| 15ms   | SQL queries, for loops, 3rd party API calls, ... | File lock on the session remains                             |
| 350ms  | PHP script ends.                                 | File lock is removed from the session file at /var/lib/php/sess_$identifier |

Whenever you start `session_start()` (or when PHP’s `session.auto_start` is set to true, it’ll do so automatically in every PHP script), the OS will lock the session file. Most implementations use `flock`, which is [also used to prevent overlapping cronjobs](https://ma.ttias.be/prevent-cronjobs-from-overlapping-in-linux/) or other file locks on Linux.

From the Linux machine, a lock on the session looks like this.

```
$ fuser /var/lib/php/session/sess_cdmtgg3noi8fb6j2vqkaai9ff5
/var/lib/php/session/sess_cdmtgg3noi8fb6j2vqkaai9ff5:  2768  2769  2770
```

`fuser` reports the 3 PIDs of processes that have (or are awaiting release of) this file locked.

```
$ lsof /var/lib/php/session/sess_cdmtgg3noi8fb6j2vqkaai9ff5
COMMAND  PID      USER   FD   TYPE DEVICE SIZE/OFF   NODE NAME
php-fpm 2769 http_demo    5uW  REG  253,1        0 655415 sess_cdmtgg3noi8fb6j2vqkaai9ff5
```

`lsof` reports you the PID and command that holds the current lock.

That lock is kept on the file until the script ends or the lock is purposely removed (more on that below). This acts as both a read *and* write lock: every attempt to read the session will have to wait until the lock is released.

The lock in itself isn’t a problem. It’s a safeguard to prevent writes to the session file from multiple locations, possibly corrupting data or removing previous data.

It becomes a problem when a second, concurrent, PHP script tries to access the same PHP session.

| Timing | script 1                                         | Linux / Server                                               | script 2                                                     |
| ------ | ------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 0ms    | `session_start();`                               | File lock (`flock`) on /var/lib/php/session/sess_$identifier set by script 1 | `session_start();` is called, but is blocking on the existing file lock. This is where PHP just "waits" for the lock to be removed. |
| 15ms   | SQL queries, for loops, 3rd party API calls, ... | File lock on the session remains                             | This script is still waiting, doing nothing.                 |
| 350ms  | Script 1 ends.                                   | File lock from script 1 is removed.                          | Script 2 still waiting ...                                   |
| 360ms  |                                                  | Script 2 gets the new file lock.                             | Script 2 can now do its SQL queries, loops, ...              |
| 700ms  |                                                  | File lock removed from script 2                              | Script 2 ends.                                               |

In case the above table isn’t very clear:

- When 2 PHP files try to start a session at the same time, only one “wins” and gets the lock. The other needs to wait.
- While it waits, it’s not doing anything: `session_start()` is blocking all further execution.
- As soon as the lock from the first script is removed, the second script can continue as it gets the lock.

**In most scenarios, this makes PHP behave as a set of synchronous scripts for the same user: one executes after the other, there are no parallel requests. Even if you tried to call these PHP files via AJAX.**

So instead of having both scripts finish in around 350ms, the first script finishes in 350ms but the second script takes twice as long and runs for 700ms because it had to wait for the first script to complete.

## Alternative session handlers: redis, memcached, mysql

If you’re looking for a quick fix and think “*I’ll just store my sessions in memcached*", you’ll be disappointed. The default configuration of memcached uses the same, safe, logic as described above: sessions are blocking as soon as one PHP call uses them.

If you’re using PHP’s Memcached extension, you can set the `memcached.sess_locking` to ‘off’ to avoid session locks. Default value is ‘on’, which makes it act like the normal session handler.

If you’re using Redis, you’re in “luck” (*) because the [redis session handler doesn’t support locking yet](https://github.com/phpredis/phpredis/issues/37). With Redis as a session storage backend, there will be no locks.

If you’re using MySQL as the session backend (like Drupal does), you have a custom implementation: there’s no default PHP extension that lets use MySQL as session storage. Somewhere in your PHP code there’s a function call to [session_set_save_handler()](https://php.net/session_set_save_handler) that describes which class or method is responsible for reading and writing your session data. That means your implementation decides whether sessions are blocking or not.

(*) see below

## PHP Session locking: the problem it’s trying to fix

It may look like I’m being overly negative on this behaviour, but I’m not. It’s just behaviour you should be aware of. It’s actually a good thing it exists, too.

Imagine the following scenario. This shows where it can go wrong. If there would be no such thing as a “session lock”, this would happen if 2 scripts are executed at the same time on the same session data:

| Timing                                                       | script 1                                                     | script 2                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 0ms                                                          | `session_start();`<br /> The session data is now in $_SESSION | `session_start();`<br /> The session data is now in $_SESSION |
| 15ms                                                         | Script 1 writes data to the session:<br/> `$_SESSION['payment_id'] = 1;` | Script 2 also writes data to the session:<br/> `$_SESSION['payment_id'] = 5;` |
| 350ms                                                        | `sleep(1);`                                                  | Script ends, saving the $_SESSION data.                      |
| 450ms                                                        | Script ends, saving its $_SESSION data.                      |                                                              |
| What value is in the session? The value from script 1. The data stored by script 2 is overwritten by the last save performed in script 1. |                                                              |                                                              |

**This is a very awkward and hard to troubleshoot concurrency bug. Session locking prevents that.**

But you may not want this. It’s mostly a problem when writing session data. If you have a PHP script that only *reads* session data (like a lot of AJAX calls would do), you can safely read the data multiple times.

On the other hand, if you have a long-running script that reads the session data and alters the values, but a second script starts and reads the old, stale, data – it can also cause problems in your application.

## Closing the PHP session lock: PHP 5.x and PHP 7

In PHP, there’s a function called [session_write_close()](http://php.net/session_write_close). It does what its name implies: it writes the session data and closes the file, thus removing the lock. In your PHP code, you can use it like this.

```
<?php
...
// This works in PHP 5.x and PHP 7
session_start();

$_SESSION['something'] = 'foo';
$_SESSION['yolo'] = 'swag';

session_write_close();

// Do the rest of your PHP execution below
```

The above opens the session (which reads the session data into `$_SESSION`), writes the data to it and closes the lock. From now on, it can’t write to that session file anymore. If more $_SESSION manipulations happen further down the script, those changes won’t be saved.

Since PHP 7 however you can [set additional options](http://php.net/manual/en/migration70.new-features.php#migration70.new-features.session-options) when you call `session_start();`.

```
<?php
session_start([
    'read_and_close' => true,
]);
?>
```

This is the rough equivalent of:

```
<?php
session_start();

session_write_close();
?>
```

It reads the session data and immediately releases the lock so other scripts won’t block on it.

## Demo: how slow is slow?

**Update: sorry, the demo-site is offline, you can look at the Github Repo to host it yourself.**

Nothing beats a good demo to show this behaviour. I’ve set up a [very simple github repository](https://github.com/mattiasgeniar/demo-php-blocking-sessions) which shows this behaviour.

It’s plain and ugly, but it shows what goes on: ~~demo.ma.ttias.be/demo-php-blocking-sessions~~.

![blocking_nonblocking_php_session_calls](https://ma.ttias.be/wp-content/uploads/2015/12/blocking_nonblocking_php_session_calls-300x232.png)

Click the image or the link above to see what it does in reality.

If you’ve been bitten by these PHP session locks, let me know. If you found an original way or workaround to resolve your particular problem I’d be even more interested!