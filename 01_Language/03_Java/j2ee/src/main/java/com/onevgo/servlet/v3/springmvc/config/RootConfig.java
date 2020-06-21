package com.onevgo.servlet.v3.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring的容器不扫描controller
 */
@ComponentScan(value = "com.onevgo.servlet.v3.springmvc", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
})
public class RootConfig {
}
