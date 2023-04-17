package miu.edu.pm.project.onlineshoppingcartsystem.product.repository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom repository methods here if needed
}
