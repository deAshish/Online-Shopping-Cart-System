package miu.edu.pm.project.onlineshoppingcartsystem.product.service.impl;

// ProductCategoryServiceImpl.java - Service Implementation

import miu.edu.pm.project.onlineshoppingcartsystem.exception.ResourceNotFoundException;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.ProductCategory;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductCategoryDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.repository.ProductCategoryRepository;

import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;



    @Autowired
    private ModelMapper modelMapper; // You can use a library like ModelMapper for mapping between entities and DTOs

    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        // Map ProductCategoryDto to ProductCategory entity
        ProductCategory productCategory = modelMapper.map(productCategoryDto, ProductCategory.class);
        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);
        // Map the saved ProductCategory entity back to ProductCategoryDto and return
        return modelMapper.map(savedProductCategory, ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto updateProductCategory(Long productCategoryId, ProductCategoryDto productCategoryDto) {
        // Retrieve the existing ProductCategory entity from the repository
        ProductCategory existingProductCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(ResourceNotFoundException::new);

        // Update the fields of the existing ProductCategory entity with the values from the ProductCategoryDto
        existingProductCategory.setName(productCategoryDto.getName());
        // Save the updated ProductCategory entity
        ProductCategory savedProductCategory = productCategoryRepository.save(existingProductCategory);
        // Map the saved ProductCategory entity back to ProductCategoryDto and return
        return modelMapper.map(savedProductCategory, ProductCategoryDto.class);
    }

    @Override
    public void deleteProductCategoryById(Long productCategoryId) {
        // Delete the ProductCategory entity from the repository
        productCategoryRepository.deleteById(productCategoryId);
    }

    @Override
    public ProductCategoryDto getProductCategoryById(Long productCategoryId) {
        // Retrieve the ProductCategory entity from the repository
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(ResourceNotFoundException::new);
//                .orElseThrow(() -> new NotFoundException("ProductCategory not found with id: " + productCategoryId));
        // Map the ProductCategory entity to ProductCategoryDto and return
        return modelMapper.map(productCategory, ProductCategoryDto.class);
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategories() {
        // Retrieve all ProductCategory entities from the repository
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        // Map the list of ProductCategory entities to a list of ProductCategoryDto objects and return
        return productCategories.stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCategoryDto> searchProductCategoryByName(String string) {
        List<ProductCategory> productCategories = productCategoryRepository.findByNameContainingIgnoreCase(string);
        return productCategories.stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }
}

