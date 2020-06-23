# 语言篇之PHP

1. [阿里云 Composer 全量镜像](https://developer.aliyun.com/composer)

   ```
   全局配置（推荐）
   所有项目都会使用该镜像地址：
   composer config -g repo.packagist composer https://mirrors.aliyun.com/composer/
   取消配置：
   composer config -g --unset repos.packagist
   
   项目配置
   仅修改当前工程配置，仅当前工程可使用该镜像地址：
   composer config repo.packagist composer https://mirrors.aliyun.com/composer/
   取消配置：
   composer config --unset repos.packagist
   
   调试
   composer 命令增加 -vvv 可输出详细的信息，命令如下：
   composer -vvv require alibabacloud/sdk
   遇到问题？
   1. 建议先将Composer版本升级到最新：
   composer self-update
   2. 执行诊断命令：
   composer diagnose
   3. 清除缓存：
   composer clear
   4. 若项目之前已通过其他源安装，则需要更新 composer.lock 文件，执行命令：
   composer update --lock
   ```

2. [PHP Session Locking How To Prevent Sessions Blocking in PHP requests](./docs/PHP-Session-Lock.md)