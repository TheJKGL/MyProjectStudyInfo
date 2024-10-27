package malakhov.study.jdbc.custom_connection_pool.impl;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Driver;
import malakhov.study.jdbc.custom_connection_pool.CustomConnection;
import malakhov.study.jdbc.custom_connection_pool.ConnectionPool;
import malakhov.study.jdbc.custom_connection_pool.ReleaseConnection;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class CustomConnectionPool implements ConnectionPool, ReleaseConnection, Closeable {

    private final Driver driver;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final BlockingQueue<Connection> connectionPool;
    private static final int INITIAL_POOL_SIZE = 10;

    public CustomConnectionPool(String driverClassName,
                                String jdbcUrl,
                                String username,
                                String password)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName(driverClassName);
        this.driver = (Driver) c.newInstance();
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.connectionPool = new LinkedBlockingDeque<>(INITIAL_POOL_SIZE);
    }

    @Override
    public CustomConnection getConnection() {
        Connection connection = connectionPool.poll();

        if (connection == null) {
            connection = createNewConnection();
        }

        return new ConnectionImpl(this, connection);
    }

    private Connection createNewConnection() {
        try {
            Properties info = new Properties();
            info.put("user", username);
            info.put("password", password);

            return driver.connect(jdbcUrl, info);
            //return DriverManager.getConnection(jdbcUrl, info);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Connection not available", t);
        }
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return connectionPool.offer(connection);
    }

    @Override
    public void close() throws IOException {
        for (Connection c : connectionPool) {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        connectionPool.clear();
    }
}
