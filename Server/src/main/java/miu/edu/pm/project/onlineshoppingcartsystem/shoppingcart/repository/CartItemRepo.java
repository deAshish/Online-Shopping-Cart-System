package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItem;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    public CartItem findByCustomer(Customer customer);
}