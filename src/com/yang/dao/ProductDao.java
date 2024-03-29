package com.yang.dao;

import com.yang.domain.Category;
import com.yang.domain.Product;
import com.yang.utils.C3P0Utils;
import com.yang.vo.Condition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Category> getAllCategory() throws SQLException {
        String sql = "select * from category";
        return qr.query(sql, new BeanListHandler<>(Category.class));
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "insert into product value (?,?,?,?,?,?,?,?,?,?)";

        qr.update(sql, product.getPid() , product.getPname()
                , product.getMarket_price() , product.getShop_price()
                , product.getPimage() , product.getPdate() , product.getIs_hot()
                , product.getPdesc() , product.getPflag() ,product.getCid());
    }

    public void deleteProductById(String pid) throws SQLException {
        String sql = "delete from product where pid = ?";
        qr.update(sql , pid);
    }

    public List<Product> getAllProductsByCondition(Condition condition) throws SQLException {
        String sql = "select * from product where 1=1 ";
        List<Object> list = new ArrayList<>();
        if (null != condition.getPname() && !condition.getPname().equals("")) {
            sql += " and pname like ? ";
            list.add("%" + condition.getPname() + "%");
        }
        if (null != condition.getCid() && !condition.getCid().equals("")) {
            sql += " and cid = ? ";
            list.add(condition.getCid());
        }
        if (null != condition.getIs_hot() && !condition.getIs_hot().equals("")) {
            sql += " and is_hot = ? ";
            list.add(condition.getIs_hot());
        }

        return qr.query(sql, new BeanListHandler<>(Product.class),list.toArray());

    }

    public void updateProductByProduct(Product product) throws SQLException {
        String sql = "update product set pname = ? , market_price = ? , shop_price = ? , pdate = ? , is_hot = ? , pdesc = ? , pflag = ? , cid = ? where pid = ?";
        qr.update(sql , product.getPname()
                , product.getMarket_price() , product.getShop_price()
                , product.getPdate() , product.getIs_hot()
                , product.getPdesc() , product.getPflag()  ,product.getCid(),product.getPid());

    }

    public List<Product> getProductsByLimit(int index , int maxCount) throws SQLException {
        String sql = "select * from product limit ? , ?";
        return qr.query(sql , new BeanListHandler<>(Product.class) , index , maxCount);
    }

    public int getTotalCount() throws SQLException {
        String sql = "select count(*) from product";
        Long l = (Long)qr.query(sql, new ScalarHandler());
        return l.intValue();
    }
}
