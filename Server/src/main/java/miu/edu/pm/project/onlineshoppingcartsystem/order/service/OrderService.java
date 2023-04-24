package miu.edu.pm.project.onlineshoppingcartsystem.order.service;



import miu.edu.pm.project.onlineshoppingcartsystem.order.dto.OrderRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.order.model.OrderCart;

import java.util.List;

public interface OrderService {
    List<OrderCart> showAll();
    List<OrderCart> searchOrder(Long searchOrder);
    OrderCart save(OrderRequest order) throws RuntimeException;
    OrderCart update(long id, OrderRequest order);
}
