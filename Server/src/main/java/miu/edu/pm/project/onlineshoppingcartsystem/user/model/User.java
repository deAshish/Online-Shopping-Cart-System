package miu.edu.pm.project.onlineshoppingcartsystem.user.model;

import javax.persistence.*;

import lombok.*;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private Integer phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String address;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemList> itemList;

    public User(String username, String password, String fullName, Integer phone, String email, Role role, String address) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(Arrays.asList(role));
    }
}
