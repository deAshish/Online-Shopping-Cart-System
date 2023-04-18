package miu.edu.pm.project.onlineshoppingcartsystem.order.controller;


import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.OrderLine;
import miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-lines")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @PostMapping
    public ResponseEntity<?> createOrderLine(@RequestBody OrderLine orderLine) {
        try {
            OrderLine createdOrderLine = orderLineService.createOrderLine(orderLine);
            return ResponseEntity.ok(createdOrderLine);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Failed to create order line", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderLine(@PathVariable Long id, @RequestBody OrderLine orderLine) {
        try {
            OrderLine updatedOrderLine = orderLineService.updateOrderLine(orderLine);
            return ResponseEntity.ok(updatedOrderLine);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Failed to update order line", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderLine(@PathVariable Long id) {
        try {
            orderLineService.deleteOrderLine(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Failed to delete order line", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderLinesByOrderId(@PathVariable Long orderId) {
        try {
            List<OrderLine> orderLines = orderLineService.getOrderLinesByOrderId(orderId);
            return ResponseEntity.ok(orderLines);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Failed to get order lines by order ID", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getOrderLinesByProductId(@PathVariable Long productId) {
        try {
            List<OrderLine> orderLines = orderLineService.getOrderLinesByProductId(productId);
            return ResponseEntity.ok(orderLines);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Failed to get order lines by product ID", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
