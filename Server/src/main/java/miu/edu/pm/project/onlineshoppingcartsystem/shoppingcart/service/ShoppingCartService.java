//package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service;
//
//import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class ShoppingCartService {
//    @Autowired
//    private CartItemService cartItemService;
//
//
//    @Autowired
//    private OrderService orderService;
//
//
//    public void addToCart(Long userId, Long productId, Integer quantity) {
//        cartItemService.addToCart(userId, productId, quantity);
//    }
//
//
//    public void removeFromCart(Long cartItemId) {
//        cartItemService.removeFromCart(cartItemId);
//    }
//
//
//    public void updateCartItemQuantity(Long cartItemId, Integer quantity) {
//        cartItemService.updateCartItemQuantity(cartItemId, quantity);
//    }
//
//
//    public void checkout(Long userId) {
//        List<CartItems> cartItems = cartItemService.getCartItemsByUserId(userId);
//
//        if (cartItems.isEmpty()) {
//            throw new NotFoundException("Shopping cart is empty");
//        }
//
//
//// Create a new order
//        Order order = new Order();
//        order.setUser(cartItems.get(0).getUser());
//        order.setStatus(OrderStatus.NEW);
//        orderService.createOrder(order);
//
//
//        // Add order lines to the new order
//        for (CartItem cartItem : cartItems) {
//            OrderLine orderLine = new OrderLine();
//            orderLine.setOrder(order);
//            orderLine.setProduct(cartItem.getProduct());
//            orderLine.setQuantity(cartItem.getQuantity());
//            orderService.createOrderLine(orderLine);
//
//
//            cartItemService.removeFromCart(cartItem.getId());
//        }
//    }
//}
