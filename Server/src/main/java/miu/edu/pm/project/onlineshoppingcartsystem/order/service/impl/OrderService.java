package miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.*;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderLineRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    public Orders createOrder(Orders orders) {
        // Calculate total price for the order
        double totalPrice = 0;
        for (OrderLine orderLine : orders.getOrderLines()) {
            totalPrice += orderLine.getSubtotal();
        }
        orders.setTotalPrice(totalPrice);

        return orderRepository.save(orders);
    }

    public Orders updateOrder(Orders orders) {
        // Calculate total price for the order
        double totalPrice = 0;
        for (OrderLine orderLine : orders.getOrderLines()) {
            totalPrice += orderLine.getSubtotal();
        }
        orders.setTotalPrice(totalPrice);

        return orderRepository.save(orders);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orders> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}
