package miu.edu.pm.project.onlineshoppingcartsystem.product.service;

import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.product.dto.ProductSearchDto;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.Product;
import miu.edu.pm.project.onlineshoppingcartsystem.product.model.ProductStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllStatus(ProductStatus status);
    List<Product> findAllByVendor(User vendor);
    List<Product> searchProductAdvanced(ProductSearchDto productSearchDto);
    Product save(ProductRequest product, User user);
    Product update(long id, ProductRequest product, User user);
    boolean delete(long id);

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

    List<Product> searchProducts(String query);
}
