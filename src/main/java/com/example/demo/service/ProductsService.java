package com.example.demo.service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productsService", value = "/products")
public class ProductsService extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("productList",products);
        request.getRequestDispatcher("getProducts.jsp").forward(request,response);
    }
}
