package com.onevgo.springboot.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    private static Log logger = LogFactory.getLog(MyApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            // 日志系统未初始化
//            logger.debug("SpringApplication starting...");
            System.out.println("SpringApplication starting...");
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            logger.debug("SpringApplication environment prepared...");
        } else if (event instanceof ApplicationPreparedEvent) {
            logger.debug("SpringApplication prepared...");
        } else if (event instanceof ApplicationReadyEvent) {
            logger.debug("SpringApplication ready...");
        } else if (event instanceof ApplicationFailedEvent) {
            logger.debug("SpringApplication failed...");
        }
    }
}
