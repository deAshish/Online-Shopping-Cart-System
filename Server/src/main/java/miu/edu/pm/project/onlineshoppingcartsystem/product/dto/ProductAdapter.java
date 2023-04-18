package miu.edu.pm.project.onlineshoppingcartsystem.product.dto;

import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;

public class ProductAdapter {
    public static ProductDto DTOFromProduct(Product product){
        ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
        return productDto;
    }

    public static Product productFromDTO(ProductDto productDTO){
        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity());
        return product;
    }
}
