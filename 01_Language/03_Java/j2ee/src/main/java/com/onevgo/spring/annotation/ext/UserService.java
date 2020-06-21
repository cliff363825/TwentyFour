package com.onevgo.spring.annotation.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        // org.springframework.context.event.ContextRefreshedEvent
        // com.onevgo.spring.annotation.IOCTest_Ext$1
        // org.springframework.context.event.ContextClosedEvent
        System.out.println("UserService.listen: " + event);
    }
}
