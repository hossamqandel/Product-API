package com.example.product_api.service;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        List<Product> product = productRepository.findAll();
        return product;
    }
    @Override
    public Product getProductById(Long id ) {
         Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }
    //else /if
    //objects
//    messages
    @Override
    public Product createProduct(Product product) {
        if (Objects.isNull(product.getName())|| product.getName().isEmpty()){
            throw new IllegalArgumentException("ProductName can not be null or empty");
        }
        if (product.getPrice()==null){
            throw new IllegalArgumentException("ProductPrice can not be null");
        }
        if (product.getPrice().compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("ProductPrice must be greater than 0");
        }
        if (Objects.isNull(product.productQuantity)){
            throw new IllegalArgumentException("ProductPrice must be greater than 0");
        }
        if (product.productQuantity<1){
            throw new IllegalArgumentException("ProductPrice must be greater than 0");
        }
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = getProductById(id);
        Optional.ofNullable(product.getName()).ifPresent(value->existingProduct.setName(value));
//
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    }

