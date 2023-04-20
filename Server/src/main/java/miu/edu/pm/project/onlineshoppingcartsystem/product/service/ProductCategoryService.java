package miu.edu.pm.project.onlineshoppingcartsystem.product.service;

// ProductService.java - Service Interface


import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductCategoryDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;


import java.util.List;


public interface ProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
    ProductCategoryDto updateProductCategory(Long productCategoryId, ProductCategoryDto productCategoryDto);
    void deleteProductCategoryById(Long productCategoryId);
    ProductCategoryDto getProductCategoryById(Long productCategoryId);
    List<ProductCategoryDto> getAllProductCategories();
    List<ProductCategoryDto> searchProductCategoryByName(String name);
}


