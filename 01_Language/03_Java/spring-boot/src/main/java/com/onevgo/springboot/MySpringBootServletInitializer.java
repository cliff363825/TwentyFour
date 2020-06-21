package com.onevgo.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 嵌入式servlet容器不会直接执行servlet 3.0+ javax.servlet.ServletContainerInitializer接口，
 * 或Spring的org.springframework.web.WebApplicationInitializer接口。
 * 这是一个有意的设计决策，旨在降低设计在war中运行的第三方库破坏Spring引导应用程序的风险。
 * <p>
 * 如果需要在Spring引导应用程序中执行servlet上下文初始化，
 * 应该注册一个实现org.springframework.boot.context.embedded.bean.ServletContextInitializer接口的bean。
 * 单一的onStartup方法提供了对ServletContext的访问，如果需要，可以很容易地用作现有WebApplicationInitializer的适配器。
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/1.5.21.RELEASE/reference/htmlsingle/#boot-features-embedded-container-context-initializer">27.3.2 Servlet Context Initialization</a>
 */
public class MySpringBootServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }
}
