package miu.edu.pm.project.onlineshoppingcartsystem.product.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductService;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import  miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        // Map ProductDto to Product entity
        Product product = modelMapper.map(productDto, Product.class);
        ProductDto createdProduct = productService.createProduct(productDto);
        // Map the created ProductDto back to Product entity and set the generated ID
        product.setId(createdProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductDto productDto) {
        // Map ProductDto to Product entity
        Product product = modelMapper.map(productDto, Product.class);
        ProductDto updatedProduct = productService.updateProduct(productId, productDto);
        // Map the updated ProductDto back to Product entity and set the ID
        product.setId(productId);
        return ResponseEntity.ok(updatedProduct);
    }

    // Get a product by ID
    @GetMapping("/{productId}")

    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

