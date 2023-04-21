package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service;

import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductAdapter;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductService;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItems;
import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository.CartItemRepo;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;




    public List<CartItems> getCartItemsByCustomerId(Long userId) {
        return cartItemRepo.findByCustomerId(userId);
    }


    public void addToCart(Long userId, Long productId, Integer quantity) {
        Customer customer = customerService.getCustomerById(userId);
        ProductDto productdto = productService.getProductById(productId);
        Product product = ProductAdapter.productFromDTO(productdto);


//            if (product == null) {
//                throw new NotFoundException("Product not found");
//            }


        List<CartItems> cartItems = cartItemRepo.findByCustomerIdAndProductId(userId, productId);
        if (!cartItems.isEmpty()) {
            CartItems cartItem = cartItems.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepo.save(cartItem);
        } else {
            CartItems cartItem = new CartItems();
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItemRepo.save(cartItem);
        }
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }

    public void updateCartItemQuantity(Long cartItemId, Integer quantity) {
        CartItems cartItem = cartItemRepo.findById(cartItemId).get();
//                .orElseThrow(() -> new NotFoundException("Cart item not found"));

        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);
    }



}

