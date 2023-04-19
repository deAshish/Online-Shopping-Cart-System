
package miu.edu.pm.project.onlineshoppingcartsystem.product.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    Long id;
    String name;
    Double price;
    int quantity;
}
