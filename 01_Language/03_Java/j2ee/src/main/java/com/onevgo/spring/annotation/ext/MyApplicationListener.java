package com.onevgo.spring.annotation.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    /**
     * 当容器中发布此事件以后，方法触发
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // org.springframework.context.event.ContextRefreshedEvent
        // com.onevgo.spring.annotation.IOCTest_Ext$1
        // org.springframework.context.event.ContextClosedEvent
        System.out.println("MyApplicationListener.onApplicationEvent: " + event);
    }
}
