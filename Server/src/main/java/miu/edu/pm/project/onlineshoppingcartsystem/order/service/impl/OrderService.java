package miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.*;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderLineRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.domain.Payment;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.ShoppingCart;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service.ShoppingCartService;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;


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
