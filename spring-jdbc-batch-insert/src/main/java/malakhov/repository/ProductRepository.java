package malakhov.repository;


import malakhov.model.Product;

import java.util.List;

public interface ProductRepository {
    void saveAll(List<Product> products);
}