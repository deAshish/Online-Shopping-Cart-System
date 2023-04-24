package miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl;

import miu.edu.pm.project.onlineshoppingcartsystem.order.dto.OrderRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.order.model.OrderCart;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.order.service.OrderService;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.repository.ItemListRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PurchaseStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.repository.UserRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private ItemListRepository itemListRepository;
    @Autowired
    private UserRepository customerRepository;

    @Override
    public List<OrderCart> showAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderCart> searchOrder(Long searchOrder) {
        return orderRepository.findAllById(new ArrayList<>(){{add(searchOrder);}});
    }

    @Override
    public OrderCart save(OrderRequest newOrder) throws RuntimeException {
        User customer = currentUserService.findLoggedUser();
        List<ItemList> items = itemListRepository.findByUserAndCreated(customer.getId());

        // VALIDATE
        if(items.isEmpty()) {
            throw new RuntimeException("Nothing to order");
        }

        OrderCart order = new OrderCart(customer,
                newOrder.getDateOrdered(),
                newOrder.getDateShipped(),
                PurchaseStatus.ORDERED,
                items);
        OrderCart createdOrder = orderRepository.save(order);
        for(ItemList item : items) {
            item.setUser(customer);
            item.setPurchaseStatus(PurchaseStatus.ORDERED);
            itemListRepository.save(item);
        }
        return createdOrder;
    }

    @Override
    public OrderCart update(long id, OrderRequest editedOrder) {
        Optional<OrderCart> optionalOrder = Optional.ofNullable(orderRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist with id: " + id));
        OrderCart orderCart = optionalOrder.get();
        if(optionalOrder.isPresent()){
            orderCart.setDateOrdered(editedOrder.getDateOrdered());
            orderCart.setDateShipped(editedOrder.getDateShipped());
            orderCart.setStatus(editedOrder.getStatus());

            orderCart = orderRepository.save(orderCart);
        }
        return orderCart;
    }
}
