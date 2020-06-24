# [php-fpm参数优化](https://linuxeye.com/380.html)

php-fpm进程设置多少合适，设成动态还是静态？

《[lnmp一键安装包](https://linuxeye.com/31.html)》中会根据你服务器内存调整php-fpm进程数。

下面是摘自Google讨论话题：《 [PHP-FPM on highload tips](https://groups.google.com/forum/#!msg/highload-php-en/5Iqfzn7hFqs/avPDwUMtQdsJ) 》。

```
When you running a highload website with PHP-FPM via FastCGI, the following tips may be useful to you
如果你的高负载网站使用PHP-FPM管理FastCGI，也许下面这些技巧对你有用

1. Compile PHP's modules as less as possible, the simple the best (fast);
尽量少安装PHP模块，最简单是最好（快）的

2. Increas PHP FastCGI child number to 100 and even more. Sometime, 200 is OK! ( On 4GB memory server);
把你的PHP FastCGI子进程数调到100或以上，在4G内存的服务器上200就可以（建议压力测试来得出自己服务器合理的值）

3. Using SOCKET PHP FastCGI, and put into /dev/shm on Linux;
socket连接FastCGI，/dev/shm是内存文件系统，socket放在内存中肯定会快些

4. Increase Linux "max open files", using the following command (must be root):
Linux下增加文件打开数，命令如下：
cat >> /etc/security/limits.conf <<EOF
* soft nproc 65535
* hard nproc 65535
* soft nofile 65535
* hard nofile 65535
EOF

5. Increase PHP-FPM open file description rlimit:
增加 PHP-FPM 打开文件描述符的限制：
# vi $php_install_dir/etc/php-fpm.conf
rlimit_files = 51200

6. Using PHP code accelerator, e.g eAccelerator, XCache. And set "cache_dir" to /dev/shm on Linux.
使用php代码加速器，例如 eAccelerator, XCache.在Linux平台上可以把`cache_dir`指向 /dev/shm
```

**/usr/local/php/etc/php-fpm.conf重要优化参数详解：**

```
pm = dynamic
```

pm参数指定了进程管理方式，有两种可供选择：static或dynamic，从字面意思不难理解，为静态或动态方式。如果是静态方式，那么在php-fpm启动的时候就创建了指定数目的进程，在运行过程中不会再有变化(并不是真的就永远不变)；而动态的则在运行过程中动态调整，当然并不是无限制的创建新进程，受pm.max_spare_servers参数影响；**动态适合小内存机器，灵活分配进程，省内存。静态适用于大内存机器，动态创建回收进程对服务器资源也是一种消耗**

```
pm.max_children = 24
```

static模式下创建的子进程数或dynamic模式下同一时刻允许最大的php-fpm子进程数量

```
pm.start_servers = 16
```

\#动态方式下的起始php-fpm进程数量

```
pm.min_spare_servers = 12
```

\#动态方式下服务器空闲时最小php-fpm进程数量

```
pm.max_spare_servers = 24
```

\#动态方式下服务器空闲时最大php-fpm进程数量

**一般php-fpm进程占用20~30m左右的内存就按30m算。如果单独跑php-fpm，动态方式起始值可设置物理内存Mem/30M，由于大家一般Nginx、MySQL都在一台机器上，于是预留一半给它们，即php-fpm进程数为$Mem/2/30。**

LNMP在一台机器上参数（仅供参考，建议压力测试得出）：

```shell
Mem=`free -m | awk '/Mem:/{print $2}'` #我的机器内存是987M
sed -i "s@^pm.max_children.*@pm.max_children = $(($Mem/2/20))@" $php_install_dir/etc/php-fpm.conf
sed -i "s@^pm.start_servers.*@pm.start_servers = $(($Mem/2/30))@" $php_install_dir/etc/php-fpm.conf
sed -i "s@^pm.min_spare_servers.*@pm.min_spare_servers = $(($Mem/2/40))@" $php_install_dir/etc/php-fpm.conf
sed -i "s@^pm.max_spare_servers.*@pm.max_spare_servers = $(($Mem/2/20))@" $php_install_dir/etc/php-fpm.conf
```

987M内存：

```
pm = dynamic
pm.max_children = 24
pm.start_servers = 16
pm.min_spare_servers = 12
pm.max_spare_servers = 24
```

