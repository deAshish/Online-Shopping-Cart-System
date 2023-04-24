package miu.edu.pm.project.onlineshoppingcartsystem.product.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.email.dto.EmailDto;
import miu.edu.pm.project.onlineshoppingcartsystem.email.service.EmailService;
import miu.edu.pm.project.onlineshoppingcartsystem.email.service.impl.EmailServiceImpl;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.PageableResponse;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductSearchDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.ProductStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.product.repository.ProductRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.product.service.ProductService;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.repository.UserRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.CurrentUserService;
import miu.edu.pm.project.onlineshoppingcartsystem.util.ListMapper;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    private static String imageDirectory = System.getProperty("user.dir") + "/images/";
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    ListMapper listMapper;


//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/")
    public List<Product> findAllApproved() {
        return productService.findAllStatus(ProductStatus.APPROVED);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found - %d !" + id));
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @GetMapping("/mycreatedproducts")
    public List<Product> findAllByVendor() {
        User user = currentUserService.findLoggedUser();
        return productService.findAllByVendor(user);
    }

    @PostMapping("/advancesearch")
    public List<Product> searchProductAdvanced(@RequestBody ProductSearchDto productAdv) {
        System.out.println("Request from front end #########" + productAdv);
        if (productAdv.getName() != "") {
            System.out.println("------------------------- 1111   ");
            if (productAdv.getStatus() != ProductStatus.APPROVED) {
                System.out.println("------------------------- 2222222222222   ");
                try {
                    if (currentUserService.findLoggedUser() != null) {
                        if (currentUserService.findLoggedUser().getRole() == Role.ADMIN ||
                            currentUserService.findLoggedUser().getRole() == Role.VENDOR) {
                            System.out.println("#########" + currentUserService.findLoggedUser().getRole());
                            return productService.searchProductAdvanced(productAdv);
                        } else {
                            productAdv.setStatus(ProductStatus.APPROVED);
                        }
                    } else {
                        productAdv.setStatus(ProductStatus.APPROVED);
                    }
                } catch (Exception e) {
                    productAdv.setStatus(ProductStatus.APPROVED);
                }
            } else {
                return productService.searchProductAdvanced(productAdv);
            }
        }
        return (List<Product>) productRepository.findAllByVendor_IdOrCategory_Id(productAdv.getIdVendor(), productAdv.getIdCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nothing :("));
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PostMapping(value = {"/saveproduct"})
    public ResponseEntity<?> save(@RequestBody ProductRequest product) {
        try {
            User user = currentUserService.findLoggedUser();
            List<User> admin = userRepository.findAllByRole(Role.ADMIN);
            sendEmail(user.getEmail(), admin.get(0).getEmail(), product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product, user));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/updateproduct/{id}")
    public Product update(@PathVariable long id, @RequestBody ProductRequest product) {
        User user = currentUserService.findLoggedUser();
        return productService.update(id, product, user);
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/deleteproduct/{id}")
    public Boolean delete(@PathVariable long id) {
        return productService.delete(id);
    }

    @GetMapping("/getcolors/")
    public List<String> getColors() {
        return productRepository.getColors();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/approve")
    public List<Product> approve() {
        List<Product> notApprovedProds = productService.findAllStatus(ProductStatus.NOTAPPROVED);
        for(Product prod : notApprovedProds) {
            prod.setStatus(ProductStatus.APPROVED);
            productRepository.save(prod);
        }
        return notApprovedProds;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/deapprove")
    public List<Product> deapprove() {
        List<Product> notApprovedProds = productService.findAllStatus(ProductStatus.NOTAPPROVED);
        for(Product prod : notApprovedProds) {
            prod.setStatus(ProductStatus.NOTAPPROVED);
            productRepository.save(prod);
        }
        return notApprovedProds;
    }

    private void sendEmail(String fromAddress, String toAddress, ProductRequest product) {
        EmailDto email = new EmailDto();
        email.setFromAddress(fromAddress);
        email.setToAddress(toAddress);
        email.setMailSubject("Please approve product");
        String msg = "Hi there " +product.getName() + product.getColor() + "created pelase approve!";
        email.setBodyText(msg);
        email.setAttachFileAddress("./generated-reports/transaction.pdf");
        EmailService emailService = new EmailServiceImpl();
        emailService.sendEmail(email);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return productService.exportReport(format);
    }

    @GetMapping({"/searchproduct/{query}"})
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String query) {
        try {
            List<Product> products = productService.searchProducts(query);
            return ResponseEntity.ok().body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
