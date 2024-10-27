package malakhov.study.jdbc.custom_connection_pool;

public interface CustomConnection {
    /**
     * Closes the connection or returns it back to the connection pool.
     */
    void close();

    /**
     * Executes the query and returns the result.
     * @param query query to execute.
     * @return result of query execution.
     */
    Object execute (Object query);
}