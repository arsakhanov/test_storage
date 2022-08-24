package com.example.demo.service;


import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.utils.MyUtils;
import com.google.gson.Gson;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "productServlet", value = "/product")
public class ProductService extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/product.jsp").forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        String vendorCode = req.getParameter("vendorCode");
        String productName = req.getParameter("productName");
        String lastPurchasePrice = req.getParameter("lastPurchasePrice");
        String lastSalePrice = req.getParameter("lastSalePrice");
        Product product = new Product();
        product.setVendorCode(Long.parseLong(vendorCode));
        product.setName(productName);
        product.setLastPurchasePrice(Double.parseDouble(lastPurchasePrice));
        product.setLastSalePrice(Double.parseDouble(lastSalePrice));
        // String output = MyUtils.getStringFromRequest(req);
        // Product product = new Gson().fromJson(output, Product.class);
        productDAO.addProduct(product);
        System.out.println("Product added");
        resp.sendRedirect("/products");
/*        PrintWriter printWriter = resp.getWriter();
        printWriter.write(addedProduct.toString());
        printWriter.close();*/
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        Product product = new Gson().fromJson(output, Product.class);
        productDAO.updateProduct(product);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(product.toString());
        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        boolean isDeleted = productDAO.deleteProduct(Long.parseLong(output));
        if(isDeleted){
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("Продукт удален");
            printWriter.close();
        }
    }
}
