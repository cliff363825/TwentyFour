package com.onevgo.springboot.elastic.config;

import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyElasticSearchConfig {

    @Bean
    public HttpClientConfigBuilderCustomizer httpClientConfigBuilderCustomizer() {
        return builder -> builder.maxTotalConnection(100).defaultMaxTotalConnectionPerRoute(5);
    }
}
