package miu.edu.pm.project.onlineshoppingcartsystem.product.repository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom repository methods here if needed

    List<Product> findByNameContainingIgnoreCase(String name);
}
