package miu.edu.pm.project.onlineshoppingcartsystem.product.dto;

import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.ProductStatus;

@Data
public class ProductRequest {

    private String name;
    private String color;
    private ProductStatus status;
    private int quantity;
    private long idCategory;
    private double price;

}
