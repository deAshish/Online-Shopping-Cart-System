package miu.edu.pm.project.onlineshoppingcartsystem.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.domain.Payment;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue
    Long id;
    Double totalPrice;
    @OneToOne
    @JoinColumn(name = "user_id")

    Customer customer;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public Orders(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Orders(Customer customer, Payment payment, List<OrderLine> orderLines) {
        this.customer = customer;
        this.payment = payment;
        this.orderLines = orderLines;
    }

    public void addOrderLine(Product product, int quantity) {
        OrderLine orderLine = new OrderLine(product, quantity);
//        orderLine.setOrders(this);
        orderLines.add(orderLine);
    }

    // Remove order line from the order
    public void removeOrderLine(OrderLine orderLine) {
//        orderLine.setOrders(null);
        orderLines.remove(orderLine);
    }

    // Calculate total price for the order
    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderLine orderLine : orderLines) {
            totalPrice = orderLine.getSubtotal();
        }
        return totalPrice;
    }

    public int getNumOrderLines() {
        return orderLines.size();
    }


}
