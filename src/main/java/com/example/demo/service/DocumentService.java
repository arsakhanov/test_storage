package com.example.demo.service;

import com.example.demo.dao.ExcelDocumentDAO;
import com.example.demo.model.Product;
import com.example.demo.utils.MyUtils;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/documents")
public class DocumentService extends HttpServlet {

    private final ExcelDocumentDAO excelDocumentDAO = new ExcelDocumentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        excelDocumentDAO.readExcelDocument();
        PrintWriter writer = resp.getWriter();
        writer.write("Документ создан");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        Product[] productArray = new Gson().fromJson(output, Product[].class);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(excelDocumentDAO.createExcelDocument(Arrays.asList(productArray)).toString());
        printWriter.close();
    }
}
