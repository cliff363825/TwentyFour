<?php
// ab -n 1000 -c 1000 http://localhost/redis_transaction.php
$redis = new Redis();
$redis->connect("127.0.0.1", 6379);

// 库存redis key
$stockKey = "stock";

$init = $_GET["init"] ?? null;
// 初始化库存
if ($init == 1) {
    $redis->set($stockKey, 100);
    echo "初始化库存成功\n";
    return;
}

// 加锁 基于乐观锁实现
$redis->watch($stockKey);
// 获取当前库存
$currentStock = $redis->get($stockKey);
if ($currentStock < 1) {
    // 商品无库存
    $redis->unwatch();
    write_log("商品已售罄");
    return;
}

// 商品有库存
// 开启事务
$redis->multi();
$currentStock -= 1;
$redis->set($stockKey, $currentStock);
$result = $redis->exec();
if ($result && $result[0] === true) {
    write_log("抢购成功, 剩余库存： $currentStock");
} else {
    write_log("抢购失败");
}

function write_log($msg)
{
    file_put_contents("log.txt", "$msg\n", FILE_APPEND | LOCK_EX);
}
