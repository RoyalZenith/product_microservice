package code.gaurav.mc_product.dtos;


import code.gaurav.mc_product.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequestDto {
    private Product product;
    private String userName;
    private String password;

}
