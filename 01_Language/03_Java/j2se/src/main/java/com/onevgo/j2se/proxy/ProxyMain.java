package com.onevgo.j2se.proxy;

import com.onevgo.j2se.reflection.Example;
import com.onevgo.j2se.reflection.IExample;

import java.lang.reflect.Proxy;

public class ProxyMain {
    public static void main(String[] args) {
        Example example = new Example();
        DumpProxyHandler<Example> handler = new DumpProxyHandler<>(example);
//        IExample proxyInstance = (IExample) Proxy.newProxyInstance(Example.class.getClassLoader(), new Class[]{IExample.class}, handler);
        IExample proxyInstance = (IExample) Proxy.newProxyInstance(Example.class.getClassLoader(), Example.class.getInterfaces(), handler);
        proxyInstance.profile();
    }
}
