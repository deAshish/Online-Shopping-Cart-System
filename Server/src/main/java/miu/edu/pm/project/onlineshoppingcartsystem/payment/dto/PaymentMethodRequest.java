package miu.edu.pm.project.onlineshoppingcartsystem.payment.dto;

import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;

@Data
public class PaymentMethodRequest {
    private Long userId;
    private Role role;
    private String type;
    private String fullname;
    private long number;
    private String expireDate;
    private int cvv;
    private int zipcode;
}
