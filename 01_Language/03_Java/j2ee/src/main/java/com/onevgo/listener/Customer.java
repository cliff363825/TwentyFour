package com.onevgo.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class Customer implements HttpSessionBindingListener, HttpSessionActivationListener, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("Customer.valueBound: " + event.getName() + " = " + event.getValue() + ": " + (event.getValue() == this));
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("Customer.valueUnbound: " + event.getName());
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("Customer.sessionWillPassivate: 从内存中写到磁盘上");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("Customer.sessionDidActivate: 从磁盘中读取出来");
    }
}
