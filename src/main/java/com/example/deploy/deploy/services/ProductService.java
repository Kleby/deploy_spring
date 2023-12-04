package com.example.deploy.deploy.services;

import com.example.deploy.deploy.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product addProduct(Product new_product);

    Product updateFullProduct(Long id, Product product);
    Product updatePriceProduct(Long id, String price);

    void deleteProductById(Long id);

}
