package malakhov.study.jdbc.custom_connection_pool;

public interface ConnectionPool {
    /**
     * Gets a connection from the pool (if it is available) or creates a new one.
     * @return connection
     */
    CustomConnection getConnection();
}
