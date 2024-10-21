package com.ca.utilities;

import lombok.Setter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    // Main Directory of the project
    public final String currentDir = System.getProperty("user.dir");

    // Location of Test data excel file
    public String testDataExcelPath;

    // Excel Sheet and Workbook
    private XSSFSheet excelWSheet;
    private XSSFWorkbook excelWBook;

    // Setter and Getters of row and columns
    // Row Number
    @Setter
    public int rowNumber;

    // Column Number
    @Setter
    public int columnNumber;

    // Set the path of the test data Excel file
    public void setTestDataExcelPath(String testDataExcelPath) {
        this.testDataExcelPath = currentDir + "/" + testDataExcelPath;
    }


    // Set the Excel sheet to be used in the workbook
    public void setExcelFileSheet(String sheetName) {
        try (FileInputStream excelFile = new FileInputStream(testDataExcelPath)) {
            // Load the workbook and get the specified sheet
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            if (excelWSheet == null) {
                throw new IllegalArgumentException("Sheet with name " + sheetName + " does not exist in the workbook.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while setting Excel file sheet: " + e.getMessage(), e);
        }
    }

    // Get the last row number with data in the Excel sheet
    public int getLastRow() {
        return excelWSheet != null ? excelWSheet.getPhysicalNumberOfRows() : 0;
    }

    // Get the last column number with data in the first row of the Excel sheet
    public int getLastCol() {
        return (excelWSheet != null && excelWSheet.getRow(0) != null) ? excelWSheet.getRow(0).getLastCellNum() : 0;
    }

    // This method reads the test data from the Excel cell, using row and column numbers as parameters
    public String getCellData(int rowNum, int colNum) {
        if (excelWSheet == null) {
            throw new IllegalStateException("Excel sheet is not set. Please set the sheet before reading data.");
        }

        XSSFRow row = excelWSheet.getRow(rowNum);
        if (row == null) {
            return "";  // Return empty string if the row is null
        }

        XSSFCell cell = row.getCell(colNum);
        if (cell == null) {
            return "";  // Return empty string if the cell is null
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // This method takes a row number as a parameter and returns the data of the given row
    public XSSFRow getRowData(int rowNum) {
        if (excelWSheet == null) {
            throw new IllegalStateException("Excel sheet is not set. Please set the sheet before accessing row data.");
        }

        return excelWSheet.getRow(rowNum);
    }

    // Close the workbook to release resources
    public void closeWorkbook() {
        if (excelWBook != null) {
            try {
                excelWBook.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing the workbook: " + e.getMessage(), e);
            }
        }
    }

}