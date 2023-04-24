package miu.edu.pm.project.onlineshoppingcartsystem.productCategory.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
