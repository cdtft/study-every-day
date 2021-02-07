package com.cdtft.concurrency.simplepool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 一个简单的连接池
 *
 * @author: wangcheng
 * @date: 2021年02月07 10:30
 */
public class ConnectionPool {

    private final LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
            } else {
                long expiredMills = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() &&  remaining > 0) {
                    pool.wait(remaining);
                    remaining = expiredMills - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
        return null;
    }
}
