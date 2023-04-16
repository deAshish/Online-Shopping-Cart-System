package miu.edu.pm.project.onlineshoppingcartsystem.order.dto;


import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.Orders;

public class OrderAdapter {
    public static Orders orderFromDTO(OrderDTO orderDTO){
        Orders orders = new Orders(orderDTO.getTotalPrice());
        return orders;
    }

    public static OrderDTO oderDTOFromOrder(Orders orders){
        OrderDTO orderDTO = new OrderDTO(orders.getTotalPrice());
        return orderDTO;
    }
}
