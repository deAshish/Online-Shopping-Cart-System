package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain;

import jakarta.persistence.*;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import java.util.List;


@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    private Integer quantity;

    @OneToOne
    private Customer customer;

    @OneToMany
    private List<CartItem> items;
}
