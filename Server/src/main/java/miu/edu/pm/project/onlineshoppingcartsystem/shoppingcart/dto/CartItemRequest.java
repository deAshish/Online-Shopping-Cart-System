package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private Product product;
    private Long customerId;
    private List<CartItemRequest> items;
}
