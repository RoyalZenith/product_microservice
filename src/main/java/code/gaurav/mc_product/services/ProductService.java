package code.gaurav.mc_product.services;

import code.gaurav.mc_product.ProductRepositories;
import code.gaurav.mc_product.models.Product;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{
    private final ProductRepositories productRepositories;
    public ProductService(ProductRepositories productRepositories){
        this.productRepositories = productRepositories;
    }
    public Product getProduct(long id){

        return productRepositories.findById(id);
    }
    public Product createProduct(Product product){
        return productRepositories.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepositories.findAll();
    }
}
