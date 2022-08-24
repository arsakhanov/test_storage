package com.example.demo.service;

import com.example.demo.dao.StorageDAO;
import com.example.demo.utils.MyUtils;
import org.hibernate.Session;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/storages")
public class StoragesService extends HttpServlet {
    private final StorageDAO storageDAO = new StorageDAO();
    Session session = MyUtils.createHibernateSession();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(session != null){
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");

            String output = MyUtils.getStringFromRequest(req);
            int index = output.indexOf(",");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
