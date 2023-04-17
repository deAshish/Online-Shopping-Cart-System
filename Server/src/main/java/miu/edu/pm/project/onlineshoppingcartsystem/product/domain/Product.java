package miu.edu.pm.project.onlineshoppingcartsystem.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Double price;
    int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    ProductCategory category;

    public Product(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
