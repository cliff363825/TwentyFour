package com.onevgo.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyAttributeListener implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("MyAttributeListener.attributeAdded: " + event.getName() + " => " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("MyAttributeListener.attributeRemoved: " + event.getName() + " => " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("MyAttributeListener.attributeReplaced: " + event.getName() + " => " + event.getValue());
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("MyAttributeListener.attributeAdded: " + srae.getName() + " => " + srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("MyAttributeListener.attributeRemoved: " + srae.getName() + " => " + srae.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("MyAttributeListener.attributeReplaced: " + srae.getName() + " => " + srae.getValue());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("MyAttributeListener.attributeAdded: " + event.getName() + " => " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("MyAttributeListener.attributeRemoved: " + event.getName() + " => " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("MyAttributeListener.attributeReplaced: " + event.getName() + " => " + event.getValue());
    }
}
