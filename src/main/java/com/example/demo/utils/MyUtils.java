package com.example.demo.utils;


import com.example.demo.model.Product;
import com.example.demo.model.Storage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class MyUtils {

    public static String getStringFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static Session createHibernateSession(){
        Session session = null;
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Storage.class);
            configuration.configure("hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
            System.out.println("Сессия создана");
        }catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }
        return session;
    }

    public static void writeTitleExcel(List<String> stringList, XSSFSheet xssfSheet){
        final int rowNum = 0;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCell cell;
        XSSFRow row;
        XSSFCellStyle style = createStyleForTitle(workbook);
        row = xssfSheet.createRow(rowNum);
        for (int i = 0; i < stringList.size(); i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(stringList.get(i));
            cell.setCellStyle(style);
        }
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}
