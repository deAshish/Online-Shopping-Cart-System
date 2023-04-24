package miu.edu.pm.project.onlineshoppingcartsystem.orderline.model;


import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PurchaseStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemList {
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Product product;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;
    private double total;
}
