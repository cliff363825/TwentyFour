package com.onevgo.springboot.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration: RabbitTemplate 的自动配置。
 * <p>仅当 RabbitMQ 和 Spring AMQP 客户端库位于类路径上时，此配置类才处于活动状态。
 * <p>注册以下bean：
 * <ul>
 * <li>RabbitTemplate                （如果上下文中没有其他相同类型的bean）。</li>
 * <li>CachingConnectionFactory实例   （如果上下文中没有其他相同类型的bean）。</li>
 * <li>AmqpAdmin实例                  （只要spring.rabbitmq.dynamic = true）。</li>
 * </ul>
 * <p>CachingConnectionFactory 支持以下属性：
 * <ul>
 * <li>spring.rabbitmq.port         用于指定客户端应连接的端口，默认为5672。</li>
 * <li>spring.rabbitmq.username     用于指定（可选）用户名。</li>
 * <li>spring.rabbitmq.password     用于指定（可选）密码。</li>
 * <li>spring.rabbitmq.host         用于指定主机，默认为localhost。</li>
 * <li>spring.rabbitmq.virtualHost  用于指定客户端应连接到的（可选）虚拟主机。</li>
 * </ul>
 * <p>说明：
 * <ul>
 * <li>1. RabbitTemplate: 给 RabbitMQ 发送和接收信息</li>
 * <li>2. ConnectionFactory: 自动配置了连接工厂</li>
 * <li>3. AmqpAdmin: RabbitMQ 系统管理功能组件，创建和删除 Queue，Exchange，Binding</li>
 * <li>4. RabbitProperties: 封装了 RabbitMQ 的配置</li>
 * <li>5. @EnableRabbit + @RabbitListener: 监听消息队列的内容</li>
 * </ul>
 */
@Configuration
@EnableRabbit // 开启基于注解的RabbitMQ模式
public class MyAMQPConfig {
    /**
     * 如果定义了 MessageConverter bean，它将自动关联到自动配置的 AmqpTemplate。
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 如有必要，任何定义为 bean 的 org.springframework.amqp.core.Queue 都会自动用于在 RabbitMQ 实例上声明相应的队列。
     *
     * @return
     */
    @Bean
    public Queue myQueue1() {
        return new Queue("my_direct_queue_1");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("my_direct_queue_2");
    }
}
