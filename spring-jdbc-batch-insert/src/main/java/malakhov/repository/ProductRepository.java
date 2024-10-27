package malakhov.repository;


import malakhov.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    void saveAll(List<Product> products);
    void saveAllRawJdbc(List<Product> products);
}