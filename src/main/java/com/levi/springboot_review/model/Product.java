package com.levi.springboot_review.model;

import com.levi.springboot_review.constant.ProductCategory;

import java.util.Date;

public class Product {
    private Integer product_id;
    private String product_name;
    private ProductCategory category;
    private String image_url;
    private Integer price;
    private Integer stock;
    private String description;
    private Date created_date;
    private Date last_modified_date;

    public Integer getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getImage_url() {
        return image_url;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
