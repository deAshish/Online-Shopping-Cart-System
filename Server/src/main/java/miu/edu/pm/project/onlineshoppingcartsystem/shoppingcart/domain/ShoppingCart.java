package miu.edu.pm.project.onlineshoppingcartsystem.shoppingcart.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItems> cartItems;
    // constructors, getters, and setters

    public ShoppingCart() {
    }

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.cartItems = new HashSet<>();
    }

    public void addItem(CartItems items) {
        cartItems.add(items);
        items.setShoppingCart(this);
    }

    public void removeItem(CartItems items) {
        cartItems.remove(items);
        items.setShoppingCart(null);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public Set<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItems> cartItems) {
        this.cartItems = cartItems;
    }
}
