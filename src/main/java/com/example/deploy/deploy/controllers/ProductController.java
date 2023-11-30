package com.example.deploy.deploy.controllers;

import com.example.deploy.deploy.DeployApplication;
import com.example.deploy.deploy.models.Product;
import com.example.deploy.deploy.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(DeployApplication.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>>getAllProducts(){
        return ResponseEntity.ok().body(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.findProductById(id));
    }

    @PostMapping("/new-product")
    public ResponseEntity<Product> AddNewProduct(@RequestBody Product newProduct){
        if(newProduct != null) return ResponseEntity.ok().body(productService.addProduct(newProduct));
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/price/{id}")
    public ResponseEntity<Product> updatePriceProduct(@PathVariable Long id, @RequestBody Map<String, Double> requestBody) {
        if (requestBody.containsKey("price")) {
            Double price = requestBody.get("price");
            var newPrice = productService.updatePriceProduct(id, price);
            return ResponseEntity.ok().body(newPrice);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
