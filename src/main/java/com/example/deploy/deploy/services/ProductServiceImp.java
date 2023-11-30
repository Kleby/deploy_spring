package com.example.deploy.deploy.services;

import com.example.deploy.deploy.DeployApplication;
import com.example.deploy.deploy.models.Product;
import com.example.deploy.deploy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImp implements ProductService {

    private static Logger log = LoggerFactory.getLogger(DeployApplication.class);

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product new_product) {
        return productRepository.save(new_product);
    }

    @Override
    public Product updateFullProduct(Long id, Product product) {
        Optional<Product> productDB = productRepository.findById(id);
        if(productDB.isPresent()){
            Product existingProduct = productDB.get();
            existingProduct.setName(product.getName());
            existingProduct.setCode(product.getCode());
            existingProduct.setPrice(product.getPrice());

            productRepository.save(existingProduct);
            return existingProduct;
        }
        return null;
    }

    @Override
    public Product updatePriceProduct(Long id, Double price) {
        Optional<Product> productDB = productRepository.findById(id);
        if(productDB.isPresent()){
            // Restante do código
            Product existingProduct = productDB.get();
            existingProduct.setPrice(price);
            productRepository.save(existingProduct);
            return existingProduct;
        }
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
