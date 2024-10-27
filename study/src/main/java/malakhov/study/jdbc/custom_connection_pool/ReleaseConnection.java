package malakhov.study.jdbc.custom_connection_pool;

import java.sql.Connection;

public interface ReleaseConnection {
    /**
     * Return SQL connection back to the pool.
     * @param connection SQL connection to return
     */
    boolean releaseConnection(Connection connection);
}