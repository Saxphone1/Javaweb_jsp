package com.yang.dao;

import com.yang.domain.Product;
import com.yang.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    private QueryRunner qr;

    public ProductDao() {
        qr = new QueryRunner(C3P0Utils.getDataSource());
    }

    public List<Product> getAllProduct() throws SQLException {
        String sql = "select * from product";
        return qr.query(sql, new BeanListHandler<>(Product.class));
    }

    public Product getProductById(String pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        return qr.query(sql, new BeanHandler<>(Product.class), pid);
    }
}
