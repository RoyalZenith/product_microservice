package code.gaurav.mc_product;

import code.gaurav.mc_product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositories extends JpaRepository<Product, Long> {

    Product findById(long id);
    Product save(Product product);

}
