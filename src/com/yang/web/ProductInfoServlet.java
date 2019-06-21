package com.yang.web;

import com.yang.domain.Product;
import com.yang.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String pid = req.getParameter("pid");
            ProductService productService = new ProductService();
            Product product = productService.getProductById(pid);
            req.setAttribute("product" , product);

            req.getRequestDispatcher("product_info.jsp").forward(req , resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req , resp);
    }
}
