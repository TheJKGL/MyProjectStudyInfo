package custom_orm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        //create table employee;
        //insert into table employee valus(1, 'Yevhenii', 'Malakhov', 800);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/employee");
        config.setUsername("user");
        config.setPassword("password");
        HikariDataSource ds = new HikariDataSource(config);

        Orm customOrm = new Orm(ds);
        Employee employee = customOrm.findById(Employee.class, 1);
        System.out.println(employee);
    }
}
