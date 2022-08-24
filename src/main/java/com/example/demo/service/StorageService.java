package com.example.demo.service;

import com.example.demo.dao.StorageDAO;
import com.example.demo.model.Storage;
import com.example.demo.utils.MyUtils;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/storage")
public class StorageService extends HttpServlet {

    private final StorageDAO storageDAO = new StorageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        List<Storage> storages = storageDAO.getAllStorages();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(storages.toString());
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        Storage storage = new Gson().fromJson(output, Storage.class);
        storageDAO.addStorage(storage);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(storage.toString());
        printWriter.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        Storage storage = new Gson().fromJson(output, Storage.class);
        Storage updatedStorage = storageDAO.updateStorage(storage);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(updatedStorage.toString());
        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        boolean isDeleted = storageDAO.deleteStorage(output);
        PrintWriter printWriter = resp.getWriter();
        if(isDeleted){
            printWriter.write("Склад удален");
        }else {
            printWriter.write("Неправильное имя склада или склад не существует");
        }
        printWriter.close();
    }
}
