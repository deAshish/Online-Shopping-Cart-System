package miu.edu.pm.project.onlineshoppingcartsystem.payment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.Orders;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue
    Long id;
    Date date;
    Double amount;
    @OneToOne
    @JoinColumn(name = "order_id")
    Orders orders;
    @OneToOne
    @JoinColumn(name = "user_id")
    Customer customer;
    @OneToOne
    @JoinColumn(name = "payment_type_id")
    PaymentType paymentType;

    public Payment(Date date, Double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Payment(Double amount) {
    }
}
