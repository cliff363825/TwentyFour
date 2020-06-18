<?php

if (!extension_loaded('redis')) {
    exit('Redis extension required!');
}

main();

function main()
{
    $success = synchronized(__METHOD__, function () {
        $str = 'begin....' . date('Y-m-d H:i:s') . '<br>';
        sleep(5);
        $str .= 'end....' . date('Y-m-d H:i:s') . '<br>';
        return $str;
    }, $result);
    var_dump($success, $result);
}

function synchronized($lockKey, $closure = null, &$result = null)
{
    $redis = RedisContext::getInstance()->getRedis();
    $lockKey = 'synchronized_' . md5($lockKey);

    if (!$redis->set($lockKey, 1, ['nx', 'ex' => 30])) {
        return false;
    }

    try {
        $result = call_user_func($closure);
        return true;
    } finally {
        $redis->del($lockKey);
    }
}

class RedisContext
{
    /**
     * @var RedisContext
     */
    private static $instance;
    /**
     * @var Redis
     */
    private $redis;
    /**
     * @var array
     */
    private $redisConfig = [
        'host' => '127.0.0.1',
        'port' => 6379,
        'timeout' => 30,
    ];

    private function __construct()
    {
    }

    /**
     * @param array $config
     * @return Redis
     * @throws Exception
     */
    public function getRedis($config = [])
    {
        if ($this->redis == null) {
            $this->redisConfig = array_merge($this->redisConfig, $config);
            $this->redis = new Redis();
            $this->connect();
        } else {
            try {
                $this->redis->ping();
            } catch (\RedisException $e) {
                $this->connect();
            }
        }

        return $this->redis;
    }

    /**
     * @throws Exception
     */
    protected function connect()
    {
        $success = $this->redis->connect($this->redisConfig['host'],
            $this->redisConfig['port'],
            $this->redisConfig['timeout']);
        if (!$success) {
            throw new \Exception('Redis has gone away!');
        }
        if (isset($this->redisConfig['password'])) {
            $this->redis->auth($this->redisConfig['password']);
        }
    }

    /**
     * @return RedisContext
     */
    public static function getInstance()
    {
        if (self::$instance === null) {
            self::$instance = new RedisContext();
        }
        return self::$instance;
    }
}
