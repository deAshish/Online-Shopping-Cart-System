package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemsController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItems>> getCartItemsByCustomerId(@PathVariable Long userId) {
        List<CartItems> cartItems = cartItemService.getCartItemsByCustomerId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{productId}/{quantity}")
    public ResponseEntity<Void> addToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Integer quantity) {
        cartItemService.addToCart(userId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{cartItemId}/{quantity}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable Long cartItemId, @PathVariable Integer quantity) {
        cartItemService.updateCartItemQuantity(cartItemId, quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
