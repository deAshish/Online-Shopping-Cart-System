package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    Product product;
    Long customer_id;
}
