package code.gaurav.mc_product.controllers;

import code.gaurav.mc_product.config.userServiceConfiguration;
import code.gaurav.mc_product.dtos.ProductRequestDto;
import code.gaurav.mc_product.dtos.ProductResponseDto;
import code.gaurav.mc_product.dtos.VerifyUserRequestDto;
import code.gaurav.mc_product.models.Product;
import code.gaurav.mc_product.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    private final ProductService productService;
    private final RestTemplateBuilder restTemplateBuilder;
    private final userServiceConfiguration userServiceConfiguration;

    public productController(ProductService productService,RestTemplateBuilder restTemplateBuilder,userServiceConfiguration userServiceConfiguration){
        this.productService = productService;
        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceConfiguration = userServiceConfiguration;
    }

    public boolean authenticate(String username, String password){
        RestTemplate restTemplate = userServiceConfiguration.getRestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(
            "http://userservice/user/verify",
                VerifyUserRequestDto.builder().userName(username).password(password).build(),
                Boolean.class
        );
        return response.getBody();
    }

    @GetMapping("get")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/get/{productId}")
    public Product getProduct(@PathVariable("productId") String productId){
        return productService.getProduct(Long.parseLong(productId));
    }
    @PostMapping(value = "/create")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productDto){
        //authentication the username and pass by calling user mc
        boolean isAuthenticate = authenticate(productDto.getUserName(),productDto.getPassword());
        if(!isAuthenticate){
            return  ProductResponseDto.builder().errorMsg("User authentication failed").build();
        }
        Product product = Product.builder().name(productDto.getProduct().getName()).price(productDto.getProduct().getPrice()).type(productDto.getProduct().getType()).build();
        Product resProduct =  productService.createProduct(product);
        return ProductResponseDto.builder().id(resProduct.getId()).name(resProduct.getName()).price(resProduct.getPrice()).type(resProduct.getType()).build();
    }

}
