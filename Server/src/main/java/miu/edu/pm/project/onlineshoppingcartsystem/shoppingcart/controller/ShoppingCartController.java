package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItem;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto.CartItemRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final CartItemService shoppingCartService;

    @GetMapping()
    public List<CartItem> getCartItems() {
        long custId = 0; // TODO Replace with current logged in customer Id
        return shoppingCartService.getShoppingCart(custId);
    }

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItemRequest cartItemRequest) {
        shoppingCartService.addProduct(cartItemRequest.getProduct(), cartItemRequest.getCustomerId());
        return "Successfully added";
    }

    @PutMapping("/{id}")
    public String updateCartItem(@PathVariable Long id, @RequestBody CartItemRequest cartItemRequest) {
        shoppingCartService.updateCartItem(id, cartItemRequest);
        return "Successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable Long id) {
        shoppingCartService.removeProduct(id);
        return "Successfully deleted";
    }

//    @GetMapping("/total")
//    public double getTotal() {
//        return shoppingCartService.getTotal();
//    }
//    @PostMapping("/checkout")
//    public void checkout() {
//        shoppingCartService.checkout();
//    }
}
