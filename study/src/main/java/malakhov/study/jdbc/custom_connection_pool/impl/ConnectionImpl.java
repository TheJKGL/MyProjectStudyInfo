package malakhov.study.jdbc.custom_connection_pool.impl;

import malakhov.study.jdbc.custom_connection_pool.CustomConnection;
import malakhov.study.jdbc.custom_connection_pool.ConnectionPool;
import malakhov.study.jdbc.custom_connection_pool.ReleaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionImpl implements CustomConnection {
    private final ConnectionPool pool;
    private final Connection connection;

    public ConnectionImpl(ConnectionPool pool, java.sql.Connection connection) {
        this.pool = pool;
        this.connection = connection;
    }

    @Override
    public void close() {
        if (pool instanceof ReleaseConnection) {
            boolean isAdded = ((ReleaseConnection) pool).releaseConnection(connection);
            if (!isAdded) {
                close(connection);
            }
        } else {
            close(connection);
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object execute(Object query) {
        if (!(query instanceof String)) {
            throw new IllegalArgumentException("String query expected");
        }

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement((String) query);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        throw new RuntimeException("Cannot execute query");
    }
}