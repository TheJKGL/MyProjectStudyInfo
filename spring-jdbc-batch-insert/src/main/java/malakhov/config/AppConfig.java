package malakhov.config;

import malakhov.repository.BatchProductRepository;
import malakhov.repository.SimpleProductRepository;
import malakhov.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public ProductService simpleProductService(SimpleProductRepository simpleProductRepository) {
        return new ProductService(simpleProductRepository, new Random(), Clock.systemUTC());
    }

    @Bean
    public ProductService batchProductService(BatchProductRepository batchProductRepository) {
        return new ProductService(batchProductRepository, new Random(), Clock.systemUTC());
    }
}