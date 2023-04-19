//package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.service;
//
//import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
//import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain.CartItem_Old;
//import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.dto.CartItemRequest;
//import miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.repository.CartItemRepo;
//import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CartItemService {
//
//    @Autowired
//    CartItemRepo cartItemRepo;
//
//    public void addProduct(Product product, Long customerId) {
//        Customer customer = null; //customerRepo.get(customerId); TODO find customer by id
//        CartItems cart = cartItemRepo.findByCustomer(customer);
//
//        CartItem_Old cartItem = null;
//
//        // find if it is already added to cart earlier
//        for (CartItem_Old item : cart.getItems()) {
//            if (item.getProduct().getName().equals(product.getName())) {
//                cartItem = item;
//                break;
//            }
//        }
//
//        // if cart item is not added to cart earlier, create new one and add it to cart
//        if (cartItem == null) {
//            cartItem = new CartItem_Old();
//            cartItem.setProduct(product);
//            cart.getItems().add(cartItem);
//        }
//
//        // Increase quantity
//        cartItem.setQuantity(cartItem.getQuantity() + 1);
//    }
//
//    public List<CartItem_Old> getShoppingCart(Long customerId) {
//        Customer customer = null; //customerRepo.get(customerId); TODO find customer by id
//        CartItem_Old cart = cartItemRepo.findByCustomer(customer);
//        return cart.getItems();
//    }
//
//    public void updateProduct(Product product, int newQuantity) {
//        Customer customer = null; //customerRepo.get(customerId); TODO find customer by id
//        CartItem_Old cart = cartItemRepo.findByCustomer(customer);
//
//        CartItem_Old cartItem = null;
//        for (CartItem_Old item : cart.getItems()) {
//            if (item.getProduct().equals(product)) {
//                item.setQuantity(newQuantity);
//                break;
//            }
//        }
//    }
//
//    public void removeProduct(Long id) {
//        List<CartItem_Old> cartItems = new ArrayList<>();
//        for (int i = 0; i < cartItems.size(); i++) {
//            if (cartItems.get(i).getId() == id) {
//                cartItems.remove(i);
//                break;
//            }
//        }
//    }
//
//    public void updateCartItem(Long id, CartItemRequest cartItemRequest) {
//
//    }
//}