package malakhov.repository;

import com.zaxxer.hikari.HikariDataSource;
import malakhov.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class SimpleProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PGSimpleDataSource pgSimpleDataSource;
    //private final HikariDataSource hikariDataSource;

    public SimpleProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.pgSimpleDataSource = new PGSimpleDataSource();
        this.pgSimpleDataSource.setUser("postgres");
        this.pgSimpleDataSource.setPassword("password");
        this.pgSimpleDataSource.setUrl("jdbc:postgresql://localhost:5432/batch-example-db");
    }

    //@Transactional
    public void saveAll(List<Product> products) {
//        for (Product product : products) {
//            jdbcTemplate.update("INSERT INTO PRODUCT (TITLE, CREATED_TS, PRICE) VALUES (?, ?, ?)",
//                    product.getTitle(), Timestamp.valueOf(product.getCreatedTs()), product.getPrice());
//        }
        try(Connection connection = pgSimpleDataSource.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(generateStatement(products.size()));
            int counter = 1;
            for (Product product : products) {
                preparedStatement.setObject(counter++, product.getTitle());
                preparedStatement.setObject(counter++, Timestamp.valueOf(product.getCreatedTs()));
                preparedStatement.setObject(counter++, product.getPrice());
            }

            int result = preparedStatement.executeUpdate();
            System.out.println("Number of updates: " + result);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateStatement(int size) {
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO PRODUCT (TITLE, CREATED_TS, PRICE) VALUES (?, ?, ?)");
        stringBuilder.repeat(", (?, ?, ?)", size - 1);
        return stringBuilder.toString();
    }
}