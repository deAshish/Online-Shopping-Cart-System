package miu.edu.pm.project.onlineshoppingcartsystem.order.model;

import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PurchaseStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderCart {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User customer;
    private LocalDate dateOrdered;
    private LocalDate dateShipped;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
    @ManyToMany
    private List<ItemList> items;

    public OrderCart(User customer, LocalDate dateOrdered, LocalDate dateShipped, PurchaseStatus status, List<ItemList> items) {
        this.customer = customer;
        this.dateOrdered = dateOrdered;
        this.dateShipped = dateShipped;
        this.status = status;
        this.items = items;
    }
}
