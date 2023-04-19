package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepo extends JpaRepository<CartItems, Long> {
    List<CartItems> findByCustomerId(Long userId);
    CartItems findByCustomerIdAndProductId(Long userId, Long productId);
    void deleteByCustomerId(Long userId);
}