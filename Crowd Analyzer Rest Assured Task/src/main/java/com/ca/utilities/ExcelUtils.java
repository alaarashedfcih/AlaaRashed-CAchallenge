package com.ca.utilities;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

@Getter
public class ExcelUtils {

    // Main Directory of the project
    public final String currentDir = System.getProperty("user.dir");

    // Location of Test data excel file
    public String testDataExcelPath;
    // Setter and Getters of row and columns
    // Row Number
    @Setter
    public int rowNumber;
    // Column Number
    @Setter
    public int columnNumber;
    // Excel WorkBook
    private XSSFWorkbook excelWBook;
    // Excel Sheet
    private XSSFSheet excelWSheet;
    // Excel cell
    private XSSFCell cell;
    // Excel row
    private XSSFRow row;

    public void setTestDataExcelPath(String testDataExcelPath) {
        this.testDataExcelPath = currentDir + "/" + testDataExcelPath;
    }


    // This method has two parameters: "Test data Excel file name" and "Excel
    // sheet name"
    // It creates FileInputStream and set Excel file and Excel sheet to
    // excelWBook and excelWSheet variables.
    public void setExcelFileSheet(String sheetName) {

        try {
            // Open the Excel file

            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public int getLastRow() {
        //		int lastRow = excelWSheet.getLastRowNum();
        return excelWSheet.getPhysicalNumberOfRows();
    }

    public int getLastCol() {
        return excelWSheet.getRow(0).getLastCellNum();
    }

    // This method reads the test data from the Excel cell.
    // We are passing row number and column number as parameters.
    public String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            throw (e);
        }
    }

    // This method takes row number as a parameter and returns the data of given
    // row number.
    public XSSFRow getRowData(int RowNum) throws Exception {
        row = excelWSheet.getRow(RowNum);
        return row;
    }

}
