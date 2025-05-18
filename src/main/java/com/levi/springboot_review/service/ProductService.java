package com.levi.springboot_review.service;

import com.levi.springboot_review.dto.ProductRequest;
import com.levi.springboot_review.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
