package com.yang.service;

import com.yang.dao.ProductDao;
import com.yang.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }

    public List<Product> getAllproduct() throws SQLException {
        return productDao.getAllProduct();
    }

    public Product getProductById(String pid) throws SQLException {
        return productDao.getProductById(pid);
    }
}
