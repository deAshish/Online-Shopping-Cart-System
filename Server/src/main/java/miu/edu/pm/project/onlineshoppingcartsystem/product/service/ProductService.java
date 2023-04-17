package miu.edu.pm.project.onlineshoppingcartsystem.product.service;

// ProductService.java - Service Interface

import java.util.List;
import java.util.Optional;

import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;


public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long productId, ProductDto productDto);
    void deleteProductById(Long productId);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
}


