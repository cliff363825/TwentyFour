package com.onevgo.springboot.amqp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.onevgo.springboot.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class BookService {
    /**
     * 当Rabbit基础设施存在时，任何bean都可以使用 @RabbitListener 进行注释，以创建侦听器端点。
     * 如果没有定义RabbitListenerContainerFactory，则自动配置一个默认的容器。
     * 如果定义了 MessageConverter 或 MessageRecoverer bean，它们将自动关联到默认工厂。
     *
     * @param book
     */
    @RabbitListener(queues = "my_direct_queue_2")
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = "my_direct_queue_1")
    public void receive02(Message message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map value = mapper.readValue(message.getBody(), Map.class);

        System.out.println(value);
        System.out.println(message.getMessageProperties());
    }
}
