package miu.edu.pm.project.onlineshoppingcartsystem.payment.model;
import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String type;
    private String fullname;
    private long number;
    private String expireDate;
    private int cvv;
    private int zipcode;
}
