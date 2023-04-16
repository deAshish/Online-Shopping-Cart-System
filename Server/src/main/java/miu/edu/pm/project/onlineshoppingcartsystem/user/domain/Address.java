package miu.edu.pm.project.onlineshoppingcartsystem.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String street;

    private String city;

    private String zip;

}
