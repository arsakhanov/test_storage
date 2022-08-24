package com.example.demo.dao;



import com.example.demo.model.Product;
import com.example.demo.model.Storage;
import com.example.demo.utils.MyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDocumentDAO {

    private final StorageDAO storageDAO = new StorageDAO();
    private int documentsNumber = 0;



    public List<Product> createExcelDocument(List<Product> products) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = workbook.createSheet("Entrance sheet");

        int rowNum = 0;
        XSSFCell cell;
        XSSFRow row;
        List<String> titleNames = new ArrayList<>();
        titleNames.add("ProductId");
        titleNames.add("Name");
        titleNames.add("LastPurchasePrice");
        titleNames.add("LastSalePrice");
        titleNames.add("Storage");
        titleNames.add("Vendor Code");
        MyUtils.writeTitleExcel(titleNames, xssfSheet);

        for (Product product : products) {
            rowNum++;
            row = xssfSheet.createRow(rowNum);

            //ProductId(A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(product.getId());
            //Name(B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(product.getName());
            //LastPurchasePrice(C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(product.getLastPurchasePrice());
            //lastSalePrice(D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(product.getLastSalePrice());
            //lastSalePrice(E)
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(product.getStorage().getName());
            //lastSalePrice(F)
            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(product.getVendorCode());
        }

        File file = new File("src/main/resources/storage.xlsx");
        file.getClass().getResource("storage.xlsx");
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        System.out.println("created file " + file.getAbsolutePath());
        return products;
    }

    public void readExcelDocument() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/storage.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Product> products = new ArrayList<>();
        String storageName = null;

        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            if (row.getRowNum() > 0) {
                Product product = new Product();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    if (row.getRowNum() == 1 && cell.getColumnIndex() == 4) {
                        storageName = cell.getStringCellValue();
                    }
                    if (cell.getRowIndex() > 0) {
                        switch (cellType) {
                            case _NONE:
                            case BLANK:
                                System.out.print("");
                                System.out.print("\t");
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue());
                                System.out.print("\t");
                                break;
                            case FORMULA:
                                // Formula
                                System.out.print(cell.getCellFormula());
                                System.out.print("\t");

                                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                                // Print out value evaluated by formula
                                System.out.print(evaluator.evaluate(cell).getNumberValue());
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue());
                                if (cell.getColumnIndex() == 0) {
                                    product.setId((long) cell.getNumericCellValue());
                                } else if (cell.getColumnIndex() == 2) {
                                    product.setLastPurchasePrice(cell.getNumericCellValue());
                                } else {
                                    product.setLastSalePrice(cell.getNumericCellValue());
                                }
                                System.out.print("\t");
                                break;
                            case STRING:
                                System.out.print(cell.getStringCellValue());
                                if (cell.getColumnIndex() == 1) {
                                    product.setName(cell.getStringCellValue());
                                }
                                System.out.print("\t");
                                break;
                            case ERROR:
                                System.out.print("!");
                                System.out.print("\t");
                                break;
                        }
                    }
                    System.out.println();
                }
                products.add(product);
            }
        }
        storageDAO.addProductInStorage(storageName, products);
    }

    public void changeStorageInProduct(List<Product> products, Storage storage) throws IOException {

        documentsNumber++;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = workbook.createSheet("Moving sheet");

        int rowNum = 0;
        XSSFCell cell;
        XSSFRow row;
        List<String> titleNames = new ArrayList<>();
        titleNames.add("Номер");
        titleNames.add("Склад 1");
        titleNames.add("Склад 2");
        titleNames.add("Список товаров");
        MyUtils.writeTitleExcel(titleNames, xssfSheet);


        for (Product product : products) {
            rowNum++;
            row = xssfSheet.createRow(rowNum);
            String oldStorage = product.getStorage().getName();
            String newStorage = storage.getName();
            product.setStorage(storage);
            //Номер(A)
            cell = row.createCell(0, CellType.NUMERIC); //сгенерировать номер
            cell.setCellValue(documentsNumber);
            //Склад 1(B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(oldStorage);
            //Склад 2(C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(newStorage);
            //Артикуль товара(D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(product.getVendorCode());
            //Наименование товара(E)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(product.getName());
            //Количество(F)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(5);
        }

        File file = new File("src/main/resources/movedProducts.xlsx");
        file.getClass().getResource("storage.xlsx");
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        System.out.println("created file " + file.getAbsolutePath());
    }
}
