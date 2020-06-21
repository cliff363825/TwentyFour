package com.onevgo.springboot.amqp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.onevgo.springboot.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAmqpApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * 单播 点对点
     */
    @Test
    public void sendDirect1() throws Exception {
        String exchange = "my_direct_exchange";
        String routingKey = "my_direct_routing_key_1";

        // void send(String exchange, String routingKey, Message message) throws AmqpException;
        // message 需要自己构造一个；定制消息体内容和消息头
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("hello world", 123, true));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        byte[] bytes = mapper.writeValueAsBytes(map);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentLength(bytes.length);

        amqpTemplate.send(exchange, routingKey, new Message(bytes, messageProperties));
        // object 默认当成消息体，只需要传入要发送的对象，自动序列化发送给 rabbitMQ
        // 对象被默认序列化以后发送出去
        // amqpTemplate.convertAndSend(exchange, routingKey, map);
    }

    @Test
    public void sendDirect2() {
        String exchange = "my_direct_exchange";
        String routingKey = "my_direct_routing_key_2";

        // object 默认当成消息体，只需要传入要发送的对象，自动序列化发送给 rabbitMQ
        // 对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend(exchange, routingKey, new Book("西游记", "吴承恩"));
    }

    /**
     * 接受数据，如何将数据自动的转为json发送出去
     */
    @Test
    public void receive() {
//        Object o = rabbitTemplate.receiveAndConvert("my_direct_queue_1"); // {msg=这是第一个消息, data=[hello world, 123, true]}
//        Object o = rabbitTemplate.receiveAndConvert("my_direct_queue_2"); // Book{bookName='西游记', author='吴承恩'}
//        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void sendFanout() {
        String exchange = "my_fanout_exchange";
        String routingKey = "my_fanout_routing_key_notfound";

        rabbitTemplate.convertAndSend(exchange, routingKey, new Book("三国演义", "罗贯中"));
        rabbitTemplate.convertAndSend(exchange, routingKey, new Book("红楼梦", "曹雪芹"));
    }

    @Test
    public void sendTopic() {
        String exchange = "my_topic_exchange";
        String routingKey1 = "my_topic_routing_key.s1.s2";
        String routingKey2 = "my_topic_routing_key.s1";

        rabbitTemplate.convertAndSend(exchange, routingKey1, new Book("三国演义", "罗贯中"));
        rabbitTemplate.convertAndSend(exchange, routingKey2, new Book("红楼梦", "曹雪芹"));
    }

    @Test
    public void createExchange() {
        // 声明一个 Direct 交换器
        Exchange exchange = new DirectExchange("my_direct_exchange");
        amqpAdmin.declareExchange(exchange);

        // 声明一个 Fanout 交换器
        exchange = new FanoutExchange("my_fanout_exchange");
        amqpAdmin.declareExchange(exchange);

        // 声明一个 Topic 交换器
        exchange = new TopicExchange("my_topic_exchange");
        amqpAdmin.declareExchange(exchange);
    }

    @Test
    public void createQueue() {
        // 创建队列
        Queue queue = new Queue("my_direct_queue_1");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_direct_queue_2");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_fanout_queue_1");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_fanout_queue_2");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_topic_queue_1");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_topic_queue_2");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_topic_queue_3");
        amqpAdmin.declareQueue(queue);

        queue = new Queue("my_topic_queue_4");
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createBinding() {
        Map<String, Object> args = new HashMap<>();
        Binding binding = new Binding("my_direct_queue_1", Binding.DestinationType.QUEUE, "my_direct_exchange", "my_direct_routing_key_1", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_direct_queue_2", Binding.DestinationType.QUEUE, "my_direct_exchange", "my_direct_routing_key_2", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_fanout_queue_1", Binding.DestinationType.QUEUE, "my_fanout_exchange", "my_fanout_routing_key_1", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_fanout_queue_2", Binding.DestinationType.QUEUE, "my_fanout_exchange", "my_fanout_routing_key_2", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_topic_queue_1", Binding.DestinationType.QUEUE, "my_topic_exchange", "my_topic_routing_key.*", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_topic_queue_2", Binding.DestinationType.QUEUE, "my_topic_exchange", "my_topic_routing_key.#", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_topic_queue_3", Binding.DestinationType.QUEUE, "my_topic_exchange", "*.my_topic_routing_key", args);
        amqpAdmin.declareBinding(binding);

        binding = new Binding("my_topic_queue_4", Binding.DestinationType.QUEUE, "my_topic_exchange", "#.my_topic_routing_key", args);
        amqpAdmin.declareBinding(binding);
    }
}
