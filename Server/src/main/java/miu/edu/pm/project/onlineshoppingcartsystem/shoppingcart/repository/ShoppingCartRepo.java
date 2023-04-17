package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.ShoppingCart;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    public ShoppingCart findByCustomer(Customer customer);
}