# 好脑子不如烂笔头系列之redis
===========================
1. Redis持久化

   1. Redis提供了2个不同形式的持久化方式

      1. RDB（Redis DataBase）
      2. AOF（Append Of File）

   2. RDB

      1. 在指定的时间间隔内将内存中的数据集快照写入磁盘，也就是行话讲的Snapshot快照，它恢复时是将快照文件直接读到内存里。

      2. 备份是如何执行的

         1. Redis会单独（fork）一个子进程来执行持久化，会先将数据写入到一个临时文件中，待持久化过程结束了，再用这个临时文件替换上次持久化好的文件。整个过程中，主进程是不进行任何IO操作的，这就确保了极高的性能如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。RDB的缺点是最后一次持久化后的数据可能丢失。

      3. rdb的保存的文件

         1. 在 redis.conf 中配置文件名称，默认为 dump.rdb

            ```
            # The filename where to dump the DB
            dbfilename dump.rdb
            ```

         2. rdb文件的保存路径，也可以修改。默认为Redis启动时命令行所在的目录下。

            ```
            # Note that you must specify a directory here, not a file name.
            dir /usr/local/redis/var
            ```

      4. rdb的保存策略

         ```
         #   In the example below the behaviour will be to save:
         #   after 900 sec (15 min) if at least 1 key changed
         #   after 300 sec (5 min) if at least 10 keys changed
         #   after 60 sec if at least 10000 keys changed
         
         save 900 1
         save 300 10
         save 60 10000
         ```

      5. 手动保存快照

         1. 命令 save：只管保存，其它不管，全部阻塞
         2. save vs bgsave

      6. stop-writes-on-bgsave-error yes

         1. 当Redis无法写入磁盘的化，直接关掉Redis的写操作

      7. rdbcompression yes

         1. 进行rdb保存时，将文件压缩

      8. rdbchecksum yes

         1. 在存储快照时，还可以让Redis使用CRC64算法来进行数据校验，但是这样做会增加大约10%的性能消耗，如果希望获取到最大的性能提升，可以关闭此功能

      9. rdb的备份

         1. 先通过config get dir，查询rdb文件的目录
         2. 将*.rdb的文件拷贝到别的地方

      10. rdb的恢复

          1. 关闭redis
          2. 先把备份的文件拷贝到工作目录下
          3. 启动Redis，备份数据会直接加载

      11. rdb的优点

          1. 节省磁盘空间
          2. 恢复速度快

      12. rdb的缺点

          1. 虽然Redis在fork时使用了写时拷贝技术，但是如果数据庞大时还是比较消耗性能。
          2. 在备份周期在一定间隔时间做一次备份，所以如果Redis意外down掉的话，就会丢失最后一次快照的所有修改。

   3. AOF

      1. 以日志的形式来记录每个写操作，将Redis执行过的所有写指令记录下来（读操作不记录），只许追加文件但不可以改写文件，Redis启动之初会读取该文件重新构建数据，换言之，Redis重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作。

      2. AOF同步频率设置

         1. 始终同步，每次Redis的写入都会立刻计入日志

         2. 每秒同步，每秒记入日志一次，如果宕机，本秒的数据可能丢失。

         3. 不主动进行同步，把同步时机交给操作系统。

            ```
            # If unsure, use "everysec".
            
            # appendfsync always
            appendfsync everysec
            # appendfsync no
            ```

      3. Rewrite

         1. AOF采用文件追加方式，文件越来越大为避免出现此情况，新增了重写机制，当AOF文件的大小超过了所设定的阈值时，Redis就会启动AOF文件的内容压缩，只保留可以恢复数据的最小指令集。可以使用命令`bgrewriteaof`

         2. Redis如何实现重写？

            1. AOF文件持续增长而过大时，会fork出一条新进程来将文件重写（也是先写临时文件最后再rename），遍历新进程的内存中数据，每条记录有一条的Set语句。重写aof文件的操作，并没有读取旧的aof文件，而是将整个内存中的数据库内容用命令的方式重写了一个新的aof文件，这点和快照有点类似。

         3. 何时重写

            1. 重写虽然可以节约大量磁盘空间，减少恢复时间，但是每次重写还是有一定的负担的，因此设定Redis要满足一定条件才会进行重写。

               ```
               auto-aof-rewrite-percentage 100
               auto-aof-rewrite-min-size 64mb
               ```

            2. 系统载入时或者上次重写完毕时，Redis会记录此时AOF大小，设为base_size，如果Redis的AOF当前大小>= base_size+base_size*100%（默认）且当前大小>=64mb（默认）的情况下，Redis会对AOF进行重写。

      4. AOF的优点

         1. 备份机制更稳健，丢失数据概率更低。
         2. 可读的日志文件，通过操作AOF文件，可以处理误操作。

      5. AOF的缺点

         1. 比起RDB占用更多的磁盘空间
         2. 恢复备份速度要慢。
         3. 每次读写都同步的话，有一定的性能压力。
         4. 存在个别Bug，造成恢复不能。

2. Redis在项目中的使用场景

   | 数据类型 | 使用场景                                                     |
   | -------- | ------------------------------------------------------------ |
   | string   | 比如说，我想知道什么时候封锁一个IP地址。incrby指令           |
   | hash     | 存储用户信息【id，name，age】<br />hset(key, field, value)<br />hset(userKey, id, 101)<br />hset(userKey, name, admin)<br />hset(userKey, age, 23)<br />——修改案例——<br />hget(userKey, id)<br />hset(userKey, id, 102) |
   | list     | 实现最新消息的排行，还可以利用list的push命令，将任务存在list列表中，同时使用另一个命令，将任务从列表中取出（pop）<br />Redis——list数据类型来模拟消息队列。（电商中的秒杀就可以采用这种方式来完成一个秒杀活动） |
   | set      | 特殊之处，可以自动去重。比如说微博中将每个人的好友存在集合（set）中，这样求两个人的共同好友的操作，我们只需要求交集即可。 |
   | zset     | 以某一个条件为权重，进行排序。<br />京东：商品详情的时候，都会有一个综合排名，还可以按照价格进行排名。 |
   
   