package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {

}
