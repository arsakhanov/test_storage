package com.example.demo.service;



import com.example.demo.dao.EntranceDAO;
import com.example.demo.model.Entrance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/entrance")
public class EntranceService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        List<Entrance> listEntrance = EntranceDAO.getEntranceDocuments();
        PrintWriter writer = resp.getWriter();
        writer.write(listEntrance.toString());
        writer.close();
    }

/*    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        String output = MyUtils.getStringFromRequest(req);
        Entrance entrance = new Gson().fromJson(output, Entrance.class);
        EntranceDAO.addEntrance(entrance);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(entrance.toString());
        printWriter.close();
    }*/

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
