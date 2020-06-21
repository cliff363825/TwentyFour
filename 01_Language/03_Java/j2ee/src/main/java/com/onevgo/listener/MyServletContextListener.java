package com.onevgo.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyServletContextListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyServletContextListener.contextInitialized: realPath => " + sce.getServletContext().getRealPath("/"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyServletContextListener.contextDestroyed: realPath => " + sce.getServletContext().getRealPath("/"));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        System.out.println("MyServletContextListener.requestDestroyed: servletPath => " + httpServletRequest.getServletPath());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        System.out.println("MyServletContextListener.requestInitialized: servletPath => " + httpServletRequest.getServletPath());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("MyServletContextListener.sessionCreated: sessionId => " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("MyServletContextListener.sessionDestroyed: sessionId => " + se.getSession().getId());
    }
}
