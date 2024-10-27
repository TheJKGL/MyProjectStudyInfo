package malakhov.repository;

import com.zaxxer.hikari.HikariDataSource;
import malakhov.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class BatchProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final HikariDataSource hikariDataSource;

    public BatchProductRepository(JdbcTemplate jdbcTemplate, HikariDataSource hikariDataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    @Transactional
    public void saveAll(List<Product> products) {
        jdbcTemplate.batchUpdate("INSERT INTO PRODUCT (TITLE, CREATED_TS, PRICE) VALUES (?, ?, ?)",
                products,
                100,
                (PreparedStatement ps, Product product) -> {
                    ps.setString(1, product.getTitle());
                    ps.setTimestamp(2, Timestamp.valueOf(product.getCreatedTs()));
                    ps.setBigDecimal(3, product.getPrice());
                });
    }

    @Override
    @Transactional
    public void saveAllRawJdbc(List<Product> products) {
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT (TITLE, CREATED_TS, PRICE) VALUES (?, ?, ?)");
        ) {
            for (Product product : products) {
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(product.getCreatedTs()));
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            //System.out.println(batchResults);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}