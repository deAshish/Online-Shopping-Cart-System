package miu.edu.pm.project.onlineshoppingcartsystem.product.service.impl;

// ProductServiceImpl.java - Service Implementation

import miu.edu.pm.project.onlineshoppingcartsystem.exception.ResourceNotFoundException;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductService;
import miu.edu.pm.project.onlineshoppingcartsystem.product.repository.ProductRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.domain.Product;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    @Autowired
    private ModelMapper modelMapper; // You can use a library like ModelMapper for mapping between entities and DTOs

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // Map ProductDto to Product entity
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        // Map the saved Product entity back to ProductDto and return
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        // Retrieve the existing Product entity from the repository
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);

        // Update the fields of the existing Product entity with the values from the ProductDto
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        // Save the updated Product entity
        Product savedProduct = productRepository.save(existingProduct);
        // Map the saved Product entity back to ProductDto and return
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteProductById(Long productId) {
        // Delete the Product entity from the repository
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        // Retrieve the Product entity from the repository
        Product product = productRepository.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        // Map the Product entity to ProductDto and return
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        // Retrieve all Product entities from the repository
        List<Product> products = productRepository.findAll();
        // Map the list of Product entities to a list of ProductDto objects and return
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductByName(String string) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(string);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}

