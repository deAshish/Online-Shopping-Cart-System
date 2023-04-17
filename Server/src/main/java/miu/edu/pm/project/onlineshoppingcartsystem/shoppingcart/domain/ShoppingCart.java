package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain;

import jakarta.persistence.*;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Customer customer;

    @OneToMany
    private List<CartItem> items;
}
