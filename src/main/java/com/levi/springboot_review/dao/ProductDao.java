package com.levi.springboot_review.dao;

import com.levi.springboot_review.dto.ProductRequest;
import com.levi.springboot_review.model.Product;

public interface ProductDao {

     Product getProductById(Integer productId);

     Integer createProduct(ProductRequest productRequest);
}
