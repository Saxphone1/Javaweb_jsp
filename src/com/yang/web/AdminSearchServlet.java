package com.yang.web;

import com.yang.domain.Category;
import com.yang.domain.Product;
import com.yang.service.ProductService;
import com.yang.vo.Condition;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class AdminSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            req.setCharacterEncoding("utf-8");
            Condition condition = new Condition();
            BeanUtils.populate(condition, req.getParameterMap());

            ProductService productService =new ProductService();
            List<Product> productList=productService.getProductByCondition(condition);
            List<Category> categoryList  = productService.getAllCategory();
//            将传达到后端的值反馈在前端页面,让跳转后有值，不会复原初始化
            req.setAttribute("productList",productList);
            req.setAttribute("categoryList",categoryList);
            req.setAttribute("condition",condition);

            req.getRequestDispatcher("/admin/product/list.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
