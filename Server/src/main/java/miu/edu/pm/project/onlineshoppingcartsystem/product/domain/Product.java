package miu.edu.pm.project.onlineshoppingcartsystem.product.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import javax.persistence.*;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Customer customer;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    ProductCategory category;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore these properties during serialization
    public Product(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
