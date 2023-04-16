package miu.edu.pm.project.onlineshoppingcartsystem.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {
    private int quantity;
    private double subtotal;
}
