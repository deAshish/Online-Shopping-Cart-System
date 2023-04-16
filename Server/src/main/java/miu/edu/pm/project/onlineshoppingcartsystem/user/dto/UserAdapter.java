package miu.edu.pm.project.onlineshoppingcartsystem.user.dto;

import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;

public class UserAdapter {
    public static Customer UserFromDTO(UserDTO userDTO){
        Customer customer = new Customer(userDTO.getId(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(),
                userDTO.getPhoneNumber(), userDTO.getAddress());
        return customer;
    }

    public static UserDTO UserDTOFromUser(Customer customer){
        UserDTO userDTO = new UserDTO(customer.getId(), customer.getUserName(), customer.getPassword(), customer.getEmail(),
                customer.getPhoneNumber(), customer.getAddress());

        return userDTO;
    }
}
