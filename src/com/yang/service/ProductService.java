package com.yang.service;

import com.yang.dao.ProductDao;
import com.yang.domain.Category;
import com.yang.domain.Product;
import com.yang.vo.Condition;

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


    public List<Category> getAllCategory() throws SQLException {
        return productDao.getAllCategory();
    }

    public void addProduct(Product product) throws SQLException {
        productDao.addProduct(product);
    }

    public void deleteProductById(String pid) throws SQLException {
        productDao.deleteProductById(pid);
    }

    public List<Product> getProductByCondition(Condition condition) throws SQLException {
        return productDao.getAllProductsByCondition(condition);

    }

    public void updateProductByProduct(Product product) throws SQLException {
        productDao.updateProductByProduct(product);
    }

    public List<Product> getProductsByLimit(int index, int maxCount) throws SQLException {
        return productDao.getProductsByLimit(index , maxCount);

    }

    public int getTotalCount() throws SQLException {
        return productDao.getTotalCount();

    }
}
