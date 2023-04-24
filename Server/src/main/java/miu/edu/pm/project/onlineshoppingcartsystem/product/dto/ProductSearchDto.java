package miu.edu.pm.project.onlineshoppingcartsystem.product.dto;

import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.ProductStatus;

@Data
public class ProductSearchDto {
    private String name;
    private String color;
    private ProductStatus status;
    private long idVendor;
    private long idCategory;

}
