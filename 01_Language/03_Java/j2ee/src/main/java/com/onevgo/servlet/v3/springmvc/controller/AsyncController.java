package com.onevgo.servlet.v3.springmvc.controller;

import com.onevgo.servlet.v3.springmvc.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long) 3000, "create fail");
        DeferredResultQueue.save(deferredResult);

        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        // 创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success======" + order;
    }

    /**
     * 1. 控制器返回 Callable
     * 2. Spring异步处理，将 Callable 提交到 TaskExecutor 使用一个隔离的线程执行
     * 3. DispatcherServlet 和所有的 Filter 退出 Web容器的线程，但是 response 保持打开状态
     * 4. Callable 返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理
     * 5. 根据 Callable 返回的结果，SpringMVC继续进行视图渲染流程等（从收请求-视图渲染）
     * <p>
     * MyFirstInterceptor.preHandle
     * 主线程开始:http-nio-8080-exec-1===>1550021641417
     * 主线程结束:http-nio-8080-exec-1===>1550021641419
     * ===========DispatcherServlet 及所有的Filter退出线程 =========
     * ===========等待 Callable 执行 ===========
     * 子线程开始:MvcAsync1===>1550021641444
     * 子线程开始:MvcAsync1===>1550021643448
     * ===========Callable 执行完成 ===========
     * MyFirstInterceptor.preHandle  /async01
     * MyFirstInterceptor.postHandle  Callable的之前的返回值就是目标方法的返回值
     * MyFirstInterceptor.afterCompletion
     * <p>
     * 异步的拦截器
     * 1. 原生 API的 AsyncListener
     * 2. SpringMVC 实现 AsyncHandlerInterceptor
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("主线程开始:" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("子线程开始:" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("子线程开始:" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
                return "Callable<String> async01";
            }
        };

        System.out.println("主线程结束:" + Thread.currentThread().getName() + "===>" + System.currentTimeMillis());
        return callable;
    }
}
