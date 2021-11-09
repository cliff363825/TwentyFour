package com.onevgo.j2se.design;

import com.onevgo.j2se.design.proxy.ExampleProxy;
import com.onevgo.j2se.design.proxy.Example;
import com.onevgo.j2se.design.proxy.IExample;

public class ProxyMain {
    public void testMyProxy() {
        IExample subject = new Example();
        IExample proxy = new ExampleProxy(subject);
        proxy.run();
    }
}
