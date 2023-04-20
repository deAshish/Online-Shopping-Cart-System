package miu.edu.pm.project.onlineshoppingcartsystem.product.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.ProductCategory;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductCategoryDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategoryCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new productCategory
    @PostMapping
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        // Map ProductCategoryDto to ProductCategory entity
        ProductCategory productCategory = modelMapper.map(productCategoryDto, ProductCategory.class);
        ProductCategoryDto createdProductCategory = productCategoryService.createProductCategory(productCategoryDto);
        // Map the created ProductCategoryDto back to ProductCategory entity and set the generated ID
        productCategory.setId(createdProductCategory.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductCategory);
    }

    // Update a productCategory
    @PutMapping("/{productCategoryId}")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(@PathVariable Long productCategoryId,
                                                    @RequestBody ProductCategoryDto productCategoryDto) {
        // Map ProductCategoryDto to ProductCategory entity
        ProductCategory productCategory = modelMapper.map(productCategoryDto, ProductCategory.class);
        ProductCategoryDto updatedProductCategory = productCategoryService.updateProductCategory(productCategoryId, productCategoryDto);
        // Map the updated ProductCategoryDto back to ProductCategory entity and set the ID
        productCategory.setId(productCategoryId);
        return ResponseEntity.ok(updatedProductCategory);
    }

    // Get a productCategory by ID
    @GetMapping("/{productCategoryId}")

    public ResponseEntity<ProductCategoryDto> getProductCategoryById(@PathVariable Long productCategoryId) {
        ProductCategoryDto productCategory = productCategoryService.getProductCategoryById(productCategoryId);
        return ResponseEntity.ok(productCategory);
    }

    // Get all productCategorys
    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAllProductCategories() {
        List<ProductCategoryDto> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(productCategories);
    }


    // Delete a productCategory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategoryById(@PathVariable("id") Long id) {
        productCategoryService.deleteProductCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductCategoryDto>> searchByName(@RequestParam("query") String query) {

        List<ProductCategoryDto> productCategories =  productCategoryService.searchProductCategoryByName(query);
        return ResponseEntity.ok(productCategories);
    }
}

