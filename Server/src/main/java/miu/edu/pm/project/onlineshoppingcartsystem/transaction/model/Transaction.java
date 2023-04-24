
package miu.edu.pm.project.onlineshoppingcartsystem.transaction.model;


import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PaymentMethod;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private PaymentMethod paymentMethod;
    private String concept;
    private double amount;
    private LocalDate dateShipped;

    public Transaction(PaymentMethod paymentMethod, String concept, double amount, LocalDate dateShipped) {
        this.paymentMethod = paymentMethod;
        this.concept = concept;
        this.amount = amount;
        this.dateShipped = dateShipped;
    }
}
