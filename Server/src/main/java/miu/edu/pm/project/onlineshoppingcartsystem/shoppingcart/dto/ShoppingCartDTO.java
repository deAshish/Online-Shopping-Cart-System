package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Long customerId;
    private List<CartItemRequest> items;
}
