package miu.edu.pm.project.onlineshoppingcartsystem.product.repository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom repository methods here if needed

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
