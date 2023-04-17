package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain;

import jakarta.persistence.*;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;


@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;

    private Integer quantity;
}
