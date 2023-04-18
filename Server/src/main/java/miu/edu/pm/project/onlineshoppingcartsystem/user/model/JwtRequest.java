package miu.edu.pm.project.onlineshoppingcartsystem.user.model;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    String username;
    String password;
}
