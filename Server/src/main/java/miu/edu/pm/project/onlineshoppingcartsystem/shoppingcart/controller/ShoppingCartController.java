package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.Orders;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service.ShoppingCartService;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{userId}/add-to-cart")
    public void addToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        shoppingCartService.addToCart(userId, productId, quantity);
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        shoppingCartService.removeFromCart(cartItemId);
    }

    @PutMapping("/cart-item/{cartItemId}")
    public void updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam Integer quantity) {
        shoppingCartService.updateCartItemQuantity(cartItemId, quantity);
    }

    @PostMapping("/{userId}/checkout")
    public void checkout(@PathVariable Long userId) {
        shoppingCartService.checkout(userId);
    }

//    @GetMapping("/{userId}/cart-item")
//    public List<CartItems> getCartItemsByCustomerId(@PathVariable Long userId) {
//        return shoppingCartService.getCartItemsByCustomerId(userId);
//    }

    @GetMapping("/customer/{userId}")
    public List<CartItems> getCartItemsByCustomerId(@PathVariable Long userId) {
        return shoppingCartService.getCartItemsByCustomerId(userId);
    }

    @PostMapping("/createOrder/{userId}")
    public Orders createOrder(@PathVariable Long userId) {
        Customer customer = customerService.getCustomerById(userId);
        return shoppingCartService.createOrder(customer);
    }


}
