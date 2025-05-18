package com.levi.springboot_review.service;

import com.levi.springboot_review.dto.ProductRequest;
import com.levi.springboot_review.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
