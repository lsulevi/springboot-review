package com.levi.springboot_review.dao.impl;

import com.levi.springboot_review.constant.ProductCategory;
import com.levi.springboot_review.dao.ProductDao;
import com.levi.springboot_review.dto.ProductQueryParams;
import com.levi.springboot_review.dto.ProductRequest;
import com.levi.springboot_review.model.Product;
import com.levi.springboot_review.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map, Integer.class );

        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date FROM product where 1=1 ";
        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, productQueryParams);

        sql = sql + " order by " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",productQueryParams.getLimit());
        map.put("offset",productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        if(productList.size() > 0){
            return  productList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO PRODUCT (product_name, category, image_url, price, stock, description, created_date, last_modified_date)" +
                "VALUE (:productName, :category, :imageUrl, :price, :stock, :description, :createDate, :lastModified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName",productRequest.getProduct_name());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImage_url());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();
        map.put("createDate",now);
        map.put("lastModified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName,category = :category, image_url = :imageUrl,price = :price,stock = :stock," +
                "description = :description ,last_modified_date = :lastModifiedDate WHERE product_id = :productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        map.put("productName", productRequest.getProduct_name());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deleteProductById(Integer productId) {

        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    private String addFilteringSql(String sql,Map<String,Object> map,ProductQueryParams productQueryParams){

        if(productQueryParams.getCategory() != null){
            sql = sql + " AND category= :productCategory";
            map.put("productCategory",productQueryParams.getCategory() .name());
        }

        if (productQueryParams.getSearch() != null){
            sql = sql + " AND product_name like :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%" );
        }

        return sql;
    }

}
