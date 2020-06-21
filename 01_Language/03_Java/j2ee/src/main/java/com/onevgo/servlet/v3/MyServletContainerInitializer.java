package com.onevgo.servlet.v3;

import com.onevgo.servlet.v3.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口等）传递过来
 * 传入感兴趣的类型
 */
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启动的时候，会运行 onStartup 方法
     * Set<Class<?>> 感兴趣的类型的所有子类型
     * ServletContext 代表当前Web应用的ServletContext，一个Web应用一个ServletContext
     * 1. 使用 ServletContext 注册 Web组件 （Servlet，Filter，Listener）
     * 2. 使用编码的方式，在项目启动的时候给 ServletContext 里面添加组件
     * 必须在项目启动的时候来添加
     * 1. ServletContainerInitializer 得到的 ServletContext
     * 2. ServletContextListener得到的ServletContext；
     *
     * @param c
     * @param ctx
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class c1 : c) {
            System.out.println(c1.getName());
        }

        // 注册组件 ServletRegistration
        ServletRegistration.Dynamic userServlet = ctx.addServlet("userServlet", UserServlet.class);
        // 配置servlet的映射信息
        userServlet.addMapping("/user");

        // 注册Listener
        ctx.addListener(UserListener.class);

        // 注册 Filter FilterRegistration
        FilterRegistration.Dynamic userFilter = ctx.addFilter("userFilter", UserFilter.class);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);
        userFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
