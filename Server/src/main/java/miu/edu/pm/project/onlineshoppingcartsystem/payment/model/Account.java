package miu.edu.pm.project.onlineshoppingcartsystem.payment.model;

import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private TypeAccount type;
    @ManyToOne
    private User user;
    private double balance;
    private String concept;
    private double taxAmount;
    private LocalDate date;

    public Account(TypeAccount type, User user, double balance, String concept, double taxAmount, LocalDate date) {
        this.type = type;
        this.user = user;
        this.balance = balance;
        this.concept = concept;
        this.taxAmount = taxAmount;
        this.date = date;
    }
}
