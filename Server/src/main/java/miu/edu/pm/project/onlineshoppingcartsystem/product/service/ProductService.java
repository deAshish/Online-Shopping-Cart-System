package miu.edu.pm.project.onlineshoppingcartsystem.product.service;

// ProductService.java - Service Interface

import java.util.List;

import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.PageableResponse;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long productId, ProductDto productDto);
    void deleteProductById(Long productId);
    ProductDto getProductById(Long productId);
    PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);

    PageableResponse<ProductDto> searchProductByName(String name, int pageNumber, int pageSize, String sortBy, String sortDir);

}


