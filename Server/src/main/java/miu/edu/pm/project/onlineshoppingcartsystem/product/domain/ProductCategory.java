package miu.edu.pm.project.onlineshoppingcartsystem.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    Long id;
    String name;
}
