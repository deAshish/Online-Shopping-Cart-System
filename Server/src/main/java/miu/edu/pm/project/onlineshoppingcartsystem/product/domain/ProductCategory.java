package miu.edu.pm.project.onlineshoppingcartsystem.product.domain;


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
public class ProductCategory {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String description;
}
