package miu.edu.pm.project.onlineshoppingcartsystem.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class UserRole {

    @Id
    private Integer userRoleId;

    private String name;

}
