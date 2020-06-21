package com.onevgo.servlet.v3;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloAsyncServlet", value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 开启异步处理 asyncSupported = true
        // 2. 开启异步模式
        System.out.println("主线程开始，" + Thread.currentThread().getName());
        AsyncContext asyncContext = request.startAsync();
        // 3. 业务逻辑进行异步处理，开始异步处理
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始，" + Thread.currentThread().getName());
                sayHello();
                // 获取到异步上下文
                AsyncContext asyncContext1 = request.getAsyncContext();
                // 4. 获取响应
                ServletResponse response1 = asyncContext1.getResponse();
                try {
                    response1.getWriter().write("hello async");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                asyncContext.complete();
                System.out.println("子线程结束，" + Thread.currentThread().getName());
            }
        });

        System.out.println("主线程结束，" + Thread.currentThread().getName());
    }

    private void sayHello() {
        System.out.println("HelloAsyncServlet.sayHello");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
