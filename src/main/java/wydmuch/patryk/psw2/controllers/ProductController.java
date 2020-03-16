package wydmuch.patryk.psw2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wydmuch.patryk.psw2.entity.Category;
import wydmuch.patryk.psw2.entity.Product;
import wydmuch.patryk.psw2.repositories.ProductRepository;

import java.util.List;

//@CrossOrigin(origins = "http://my-shop-123.surge.sh", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
public class ProductController {

    //TODO paginacja

    private final
    ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("products")
    List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("products/{category}")
    List<Product> getProductsCat(@PathVariable String category){
        return productRepository.findByCategory(Category.valueOf(category.toUpperCase()));
    }

}
