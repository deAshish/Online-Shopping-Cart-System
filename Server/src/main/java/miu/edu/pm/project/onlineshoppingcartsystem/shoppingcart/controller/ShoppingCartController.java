package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItem;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto.CartItemRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping()
    public List<CartItem> getCartItems() {
        long custId = 0; // TODO Replace with current logged in customer Id
        return shoppingCartService.getShoppingCart(custId);
    }

    @PostMapping("/add")
    public String addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        shoppingCartService.addProduct(cartItemRequest.getProduct(), cartItemRequest.getCustomer_id());
        return "Successfully added";
    }
}
