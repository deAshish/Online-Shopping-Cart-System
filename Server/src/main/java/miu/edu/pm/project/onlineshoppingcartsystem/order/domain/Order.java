package miu.edu.pm.project.onlineshoppingcartsystem.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.domain.Payment;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue
    Long id;
    Double totalPrice;
    @OneToOne
    @JoinColumn(name = "user_id")

    Customer customer;
    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;

    List<OrderLine> orderLines;

    public Order(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
