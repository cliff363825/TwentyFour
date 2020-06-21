package com.onevgo.context;

import java.sql.Connection;

public class ConnectionContext {
    private static ConnectionContext instance = new ConnectionContext();
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    private ConnectionContext() {
    }

    public static ConnectionContext getInstance() {
        return instance;
    }

    public void bind(Connection connection) {
        connectionThreadLocal.set(connection);
    }

    public Connection get() {
        return connectionThreadLocal.get();
    }

    public void remove() {
        connectionThreadLocal.remove();
    }

}
