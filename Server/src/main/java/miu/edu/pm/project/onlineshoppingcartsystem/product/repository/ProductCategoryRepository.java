package miu.edu.pm.project.onlineshoppingcartsystem.product.repository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    // You can add custom repository methods here if needed

    List<ProductCategory> findByNameContainingIgnoreCase(String name);
}
