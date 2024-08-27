package malakhov.study.jdbc.lesson1;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JdbcTutorial {
    //mem (in memory) - The DB lives as long as connection is open
    //run init script - jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql'
    //';' - separates options from each other
    private static final String initScriptPath = "root-study/main/java/malakhov/study/jdbc/lesson1/users.sql";
    private static final String url = String.format("jdbc:h2:mem:;INIT=RUNSCRIPT FROM '%s'", initScriptPath);

    public static void main(String[] args) throws SQLException {

        //new way
        DataSource dataSource = createDataSource();

        //old way: DriverManager.getConnection(url)
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("connection.isValid(0) = " + connection.isValid(0));
            // CRUD

            //select
            PreparedStatement ps = connection.prepareStatement("select * from USERS where first_name = ?");
            //starts from 1 (not from 0)
            ps.setString(1, "Marco");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                System.out.println(user);
            }

            //inserts
            PreparedStatement insertPs = connection.prepareStatement("insert into USERS (first_name) values (?)");
            insertPs.setString(1, "John");
            //executeUpdate is used for insert, delete, update.
            int insertCount = insertPs.executeUpdate();
            System.out.println("insertCount = " + insertCount);

            //updates
            PreparedStatement updatePs = connection.prepareStatement("update USERS set first_name = ? where first_name = ?");
            updatePs.setString(1, "Johnny");
            updatePs.setString(2, "John");
            int updateCount = updatePs.executeUpdate();
            System.out.println("updateCount = " + updateCount);

            //deletes
            PreparedStatement deletePs = connection.prepareStatement("delete from USERS where first_name = ?");
            deletePs.setString(1, "Johnny");
            int deleteCount = deletePs.executeUpdate();
            System.out.println("deleteCount = " + deleteCount);

            //pretty print
            PreparedStatement selectAll = connection.prepareStatement("select * from USERS");
            ResultSet selectAllResult = selectAll.executeQuery();
            printResultSet(selectAllResult);

            //RowSet
            try (JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
                jdbcRowSet.setUrl(url);
                jdbcRowSet.setCommand("select * from USERS");
                jdbcRowSet.execute();

                while (jdbcRowSet.next()) {
                    // Print and display commands
                    System.out.println("ID: "
                            + jdbcRowSet.getInt(1));
                    System.out.println("FIRST_NAME: "
                            + jdbcRowSet.getString(2));
                    System.out.println("LAST_NAME: "
                            + jdbcRowSet.getString(3));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        return ds;
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", metaData.getColumnName(i));
        }
        System.out.println();

        // Print a line separator
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("--------------------");
        }
        System.out.println();

        // Print each row
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
    }
}
