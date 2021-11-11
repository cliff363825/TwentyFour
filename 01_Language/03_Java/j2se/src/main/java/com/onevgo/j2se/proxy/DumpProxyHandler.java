package com.onevgo.j2se.proxy;

import lombok.extern.slf4j.Slf4j;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DumpProxyHandler<T> implements InvocationHandler {
    // 被代理对象
    private T target;

    public DumpProxyHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        byte[] bytes = ProxyGenerator.generateProxyClass(proxy.getClass().getName(), proxy.getClass().getInterfaces());

        String filename = proxy.getClass().getName().replace('.', '/');
        File file = new File(filename + ".class");
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            log.info("Dump success={}", file.getPath());
        }

        return method.invoke(target, args);
    }
}
