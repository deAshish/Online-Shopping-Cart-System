package miu.edu.pm.project.onlineshoppingcartsystem.product.repository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    // You can add custom repository methods here if needed
}
