package miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.OrderLine;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderLineRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;


    public OrderLine createOrderLine(OrderLine orderLine) {
        // Save the order line
        return orderLineRepository.save(orderLine);
    }

    public OrderLine updateOrderLine(OrderLine orderLine) {
        // Save the updated order line
        return orderLineRepository.save(orderLine);
    }

    public void deleteOrderLine(Long id) {
        // Delete the order line by ID
        orderLineRepository.deleteById(id);
    }

    public List<OrderLine> getOrderLinesByOrderId(Long orderId) {
        // Get all order lines associated with the given order ID
        return orderLineRepository.findByOrdersId(orderId);
    }

    public List<OrderLine> getOrderLinesByProductId(Long productId) {
        // Get all order lines associated with the given product ID
        return orderLineRepository.findByProductId(productId);
    }
}
