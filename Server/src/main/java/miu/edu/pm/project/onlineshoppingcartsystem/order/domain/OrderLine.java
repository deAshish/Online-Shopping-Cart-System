package miu.edu.pm.project.onlineshoppingcartsystem.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLine {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    

    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders orders;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Calculate subtotal for this order line
    public double getSubtotal() {
        return product.getPrice()*(quantity);
    }

    // Get product name for this order line
    public String getProductName() {
        return product.getName();
    }

    // Get product price for this order line
    public double getProductPrice() {
        return product.getPrice();
    }
}
