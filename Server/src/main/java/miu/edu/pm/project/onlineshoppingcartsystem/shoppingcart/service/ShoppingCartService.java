package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.OrderLine;
import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.OrderStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.Orders;
import miu.edu.pm.project.onlineshoppingcartsystem.order.repository.OrderRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl.OrderLineService;
import miu.edu.pm.project.onlineshoppingcartsystem.order.service.impl.OrderService;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.domain.Payment;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.ShoppingCart;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository.ShoppingCartRepo;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private OrderRepository orderRepository;


    public void addToCart(Long userId, Long productId, Integer quantity) {
        cartItemService.addToCart(userId, productId, quantity);
    }


    public void removeFromCart(Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
    }


    public void updateCartItemQuantity(Long cartItemId, Integer quantity) {
        cartItemService.updateCartItemQuantity(cartItemId, quantity);
    }


    public void checkout(Long userId) {
        List<CartItems> cartItems = cartItemService.getCartItemsByCustomerId(userId);

//        if (cartItems.isEmpty()) {
//            throw new NotFoundException("Shopping cart is empty");
//        }


// Create a new order
        Orders order = new Orders();
        order.setCustomer(cartItems.get(0).getCustomer());
        order.setStatus(OrderStatus.NEW);
        orderService.createOrder(order);


        // Add order lines to the new order
        for (CartItems cartItem : cartItems) {
            OrderLine orderLine = new OrderLine();
            orderLine.setOrders(order);
            orderLine.setProduct(cartItem.getProduct());
            orderLine.setQuantity(cartItem.getQuantity());
            orderLineService.createOrderLine(orderLine);
//            orderLineService.createOrderLine(orderLine);

            cartItemService.removeFromCart(cartItem.getId());
        }
    }

    public List<CartItems> getCartItemsByCustomerId(Long userId) {
        return cartItemService.getCartItemsByCustomerId(userId);
    }

    public ShoppingCart getShoppingCartByCustomerId(Long customerId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findByCustomerId(customerId);
//        if (optionalShoppingCart.isPresent()) {
            return optionalShoppingCart.get();
//        }
//        else {
//            throw new ShoppingCartNotFoundException("Shopping cart not found for user ID: " + userId);
//        }
    }


    public Orders createOrder(Customer customer) {
        ShoppingCart shoppingCart = getShoppingCartByCustomerId(customer.getId());
        Set<CartItems> cartItems = shoppingCart.getCartItems();
        Payment payment = new Payment(shoppingCart.getTotalPrice(shoppingCart));

//        Orders order = new Orders(customer, payment, new ArrayList<>());
        Orders order = new Orders(customer, new ArrayList<>());

        for (CartItems cartItem : cartItems) {
            order.addOrderLine(cartItem.getProduct(), cartItem.getQuantity());
        }
        order.setTotalPrice(shoppingCart.getTotalPrice());
        shoppingCartRepo.delete(shoppingCart);
        return orderRepository.save(order);
    }

//    public Orders createOrder(Customer customer) {
//        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByCustomerId(customer.getId());
//
//        // create a new Payment instance
//        Payment payment = new Payment();
//
//        // set the payment details here, if needed
//
//        // create a new order instance
//        Orders order = new Orders(customer, shoppingCart.getTotalPrice(), payment);
//
//        // create order lines from the items in the shopping cart
//        List<OrderLine> orderLines = new ArrayList<>();
//        for (CartItems item : shoppingCart.getCartItems()) {
//            Product product = item.getProduct();
//            int quantity = item.getQuantity();
//            OrderLine orderLine = new OrderLine(product, quantity);
//            orderLines.add(orderLine);
//        }
//
//        // add the order lines to the order
//        order.setOrderLines(orderLines);
//
//        // save the order and order lines
//        orderRepository.save(order);
//        orderLineRepository.saveAll(orderLines);
//
//        // clear the shopping cart
//        shoppingCartService.clearCart(shoppingCart);
//
//        return order;
//    }

    public void clearCart(ShoppingCart shoppingCart) {
        shoppingCart.getCartItems().clear();
        shoppingCart.setTotalPrice(0.0);
        shoppingCartRepo.save(shoppingCart);
    }

}
