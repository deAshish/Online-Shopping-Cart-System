package miu.edu.pm.project.onlineshoppingcartsystem.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;
import miu.edu.pm.project.onlineshoppingcartsystem.productCategory.model.Category;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;

    @JsonIgnore
    @ManyToOne
    private User vendor;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private int quantity;

    @JsonIgnore
    @ManyToOne
    private Category category;
    private double price;

    @JsonIgnore
    @OneToMany
    private List<ItemList> itemList;
    private String description;



    public Product(String name, String color, User vendor, ProductStatus status, int quantity, Category category, double price) {
        this.name = name;
        this.color = color;
        this.vendor = vendor;
        this.status = status;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }
}
