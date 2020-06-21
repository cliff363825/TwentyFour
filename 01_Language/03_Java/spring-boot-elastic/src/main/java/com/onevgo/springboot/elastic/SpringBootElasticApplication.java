package com.onevgo.springboot.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot 默认支持两种技术来和 ES交互
 * 1. Jest 默认不生效
 *  需要导入 jest 的工具包 io.searchbox.client.JestClient
 * 2. SpringData ElasticSearch【ES版本有可能不合适】
 *  版本适配说明：
 *  如果版本不适配 2.4.6
 *      1. 升级Springboot版本
 *      2. 安装对应版本的ES
 *  1. Client 节点信息clusterNodes clusterName
 *  2. ElasticsearchTemplate 操作es
 *  3. 编写一个 ElasticsearchRepository 的子接口来操作ES
 * 两种用法
 *  1. 编写一个 ElasticsearchRepository
 *  2.
 */
@SpringBootApplication
public class SpringBootElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticApplication.class, args);
    }

}
