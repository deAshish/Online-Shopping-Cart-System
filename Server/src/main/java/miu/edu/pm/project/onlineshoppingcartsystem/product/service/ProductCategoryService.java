package miu.edu.pm.project.onlineshoppingcartsystem.product.service;

// ProductService.java - Service Interface


import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductCategoryDto;


import java.util.List;


public interface ProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);
    ProductCategoryDto updateProductCategory(Long productCategoryId, ProductCategoryDto productCategoryDto);
    void deleteProductCategoryById(Long productCategoryId);
    ProductCategoryDto getProductCategoryById(Long productCategoryId);
    List<ProductCategoryDto> getAllProductCategories();
}


