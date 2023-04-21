package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

@Entity
@AllArgsConstructor
@Data
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)  private Customer customer;

// constructors, getters, and setters

    public CartItems() {
    }

    public CartItems(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return product.getPrice() * quantity;
    }

}
