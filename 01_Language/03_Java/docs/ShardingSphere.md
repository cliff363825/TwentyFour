# Apache ShardingSphere

## 课程内容介绍

1. 基本概念
   1. 什么是Sharding Sphere
   2. 分库分表

2. Sharding-JDBC 分库分表操作

3. Sharding-Proxy分库分表操作

### 什么是ShardingSphere

1. 一套开源的分布式数据库中间件解决方案
2. 有三个产品：Sharding-JDBC和Sharding-Proxy
3. 定位为关系型数据库中间件，合理在分布式环境下使用关系型数据库操作

### 什么是分库分表

1. 数据库数据量不可控的，随着时间和业务发展，造成表里面数据越来越多，如果再去对数据库crud操作时候，造成性能问题。
2. 方案1:从硬件上
3. 方案2:分库分表
4. 为了解决由于数据量过大而造成数据库性能降低问题。

### 分库分表的方式

1. 分库分表有两种方式：垂直切分和水平切分
2. 垂直切分：垂直分表和垂直分库
3. 水平切分：水平分表和水平分库
4. 垂直分表：
   1. 操作数据库中某张表，把这张表中一部分字段数据放到一张新表里面，再把这张表另一部分字段数据存放到另外一张表里面
5. 垂直分库：
   1. 把单一数据库按照业务进行拆分，专库专表
6. 水平分库：
7. 水平分表

### 分库分表应用和问题

1. 应用
   1. 在数据库设计时候考虑垂直分库和垂直分表
   2. 随着数据库数据增加，不要马上考虑做水平拆分，首先考虑缓存处理，读写分离，使用索引等等方式，如果这些方式不能根本解决问题了，在考虑做水平分库和分表
2. 分库分表问题
   1. 跨节点连接查询问题（分页、排序）
   2. 多数据源管理问题

## Sharding-JDBC简介

1. 是轻量级的java框架，是增强版的JDBC驱动

2. Sharding-JDBC

   1. 主要目的是：简化对分库分表之后数据相关操作

      ![彻底搞清分库分表（垂直分库，垂直分表，水平分库，水平分表）](./images/v2-827d763eb5eb16d14545fe408e9f31fe_1440w.jpg)

### Sharding-JDBC实现水平分表

1. 搭建环境

   1. 技术：SpringBoot 2.2.1 + MyBatisPlus + Sharding-JDBC + Druid连接池

   2. 创建SpringBoot工程

   3. 修改工程SpringBoot版本2.2.1

      ```xml
      <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.9.BUILD-SNAPSHOT</version>
        <relativePath/> <!-- lookup parent from repository -->
      </parent>
      ```

   4. 引入需要的依赖

      ```xml
      <dependencies>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
        </dependency>
      
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <scope>runtime</scope>
        </dependency>
        <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <optional>true</optional>
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
          <exclusions>
            <exclusion>
              <groupId>org.junit.vintage</groupId>
              <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
          </exclusions>
        </dependency>
      
        <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>druid-spring-boot-starter</artifactId>
          <version>1.1.20</version>
        </dependency>
        <dependency>
          <groupId>org.apache.shardingsphere</groupId>
          <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
          <version>4.0.0-RC1</version>
        </dependency>
        <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus-boot-starter</artifactId>
          <version>3.0.5</version>
        </dependency>
      </dependencies>
      ```

2. 按照水平分表的方式，创建数据库和数据库表

   1. 创建数据库course_db
   2. 在数据库创建两张表course_1和course_2
   3. 约定规则：如果添加课程id是偶数把数据添加course_1，如果奇数添加到course_2

3. 编写代码实现对分库分表后数据的操作

   1. 创建实体类，mapper

4. 配置Sharding-JDBC分片策略

   1. 在项目application.properties配置文件中进行配置
   2. 