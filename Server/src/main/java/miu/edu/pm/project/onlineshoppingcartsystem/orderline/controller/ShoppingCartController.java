package miu.edu.pm.project.onlineshoppingcartsystem.orderline.controller;


import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.repository.ItemListRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PurchaseStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.repository.UserRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingcart")
@PreAuthorize("hasAuthority('CUSTOMER')")

public class ShoppingCartController {

    @Autowired
    ItemListRepository itemListRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getItems")
    List<ItemList> getItems() {
        // Get logged user info
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Get user details from database
        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));
        return itemListRepository.findByUserAndCreated(user.getId());
    }

    /**
     * Customer can add item to the shopping cart
     * @param newItem New item to add shopping cart
     * @return Returns all items in the shopping cart
     */
    @PostMapping("/add")
    List<ItemList> add(@RequestBody ItemList newItem) {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));

        newItem.setUser(user);
        newItem.setPurchaseStatus(PurchaseStatus.CREATED);
        itemListRepository.save(newItem);
        return itemListRepository.findByUserAndCreated(user.getId());
    }

    @PostMapping("/update/{id}")
    List<ItemList> update(@RequestBody ItemList itemParam, @PathVariable Long id) {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));

        // Update if item exists, else create it in database
        return itemListRepository.findById(id)
                .map(item -> {
                    item.setQuantity(itemParam.getQuantity());
                    item.setTotal(itemParam.getQuantity() * item.getProduct().getPrice());
                    itemListRepository.save(item);
                    return itemListRepository.findByUserAndCreated(user.getId());
                })
                .orElseGet(() -> {
//                    itemParam.setId(id);
                    itemParam.setUser(user);
                    itemParam.setPurchaseStatus(PurchaseStatus.CREATED);
                    itemListRepository.save(itemParam);
                    return itemListRepository.findByUserAndCreated(user.getId());
                });
    }

    @DeleteMapping("/deleteItem/{id}")
    List<ItemList> delete(@PathVariable Long id) {

        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));

        itemListRepository.deleteById(id);
        return itemListRepository.findByUserAndCreated(user.getId());
    }
}
