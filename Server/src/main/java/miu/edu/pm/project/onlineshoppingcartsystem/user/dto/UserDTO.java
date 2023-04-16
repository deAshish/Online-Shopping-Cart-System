package miu.edu.pm.project.onlineshoppingcartsystem.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Address;
import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Role;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String userName;
    String password;
    String email;
    String phoneNumber;
    Address address;
}
