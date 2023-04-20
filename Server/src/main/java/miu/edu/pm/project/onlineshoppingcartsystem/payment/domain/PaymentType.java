package miu.edu.pm.project.onlineshoppingcartsystem.payment.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentType {
    @Id
    @GeneratedValue
    Long id;
    String name;
}
