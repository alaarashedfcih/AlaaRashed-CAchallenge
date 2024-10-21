package com.ca.utilities;

import com.ca.dataproviderobjects.excel.CategoriesData;
import com.ca.dataproviderobjects.excel.ProductsData;
import com.ca.dataproviderobjects.excel.ServicesData;
import com.ca.dataproviderobjects.excel.StoresData;
import com.rits.cloning.Cloner;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderSource {







    /***************************Categories Data****************************************/

    @DataProvider(name = "CategoriesDataFeed")
    public Iterator<Object> categoriesDataFeed(Method method) {
        String testCaseName = method.getName();
        CategoriesData categoriesData = new CategoriesData();
        return fetchDataFromSheet(categoriesData, Constants.CATEGORIES_WORKBOOK, Constants.CATEGORIES_SHEET,
                testCaseName);
    }

    /***************************Services Data****************************************/

    @DataProvider(name = "ServicesDataFeed")
    public Iterator<Object> servicesDataFeed(Method method) {
        String testCaseName = method.getName();
        ServicesData servicesData = new ServicesData();
        return fetchDataFromSheet(servicesData, Constants.SERVICES_WORKBOOK, Constants.SERVICES_SHEET,
                testCaseName);
    }

    /***************************Stores Data****************************************/

    @DataProvider(name = "StoresDataFeed")
    public Iterator<Object> storesDataFeed(Method method) {
        String testCaseName = method.getName();
        StoresData storesData = new StoresData();
        return fetchDataFromSheet(storesData, Constants.STORES_WORKBOOK, Constants.STORES_SHEET,
                testCaseName);
    }

    /***************************Products Data****************************************/

    @DataProvider(name = "ProductsDataFeed")
    public Iterator<Object> productsDataFeed(Method method) {
        String testCaseName = method.getName();
        ProductsData productsData = new ProductsData();
        return fetchDataFromSheet(productsData, Constants.PRODUCTS_WORKBOOK, Constants.PRODUCTS_SHEET,
                testCaseName);
    }



    /***************************Fetch Data From Excel Sheet****************************************/
    public Iterator<Object> fetchDataFromSheet(Object obj, String workBookName, String sheetName, String testCaseName) {
        ExcelUtils config = new ExcelUtils();
        config.setTestDataExcelPath("src/main/resources/testdata/" + workBookName);
        config.setExcelFileSheet(sheetName);
        int row = config.getLastRow();
        List<Object> recordsList = new ArrayList<>();
        Cloner cloner = new Cloner();

        for (int i = 1; i < row; i++) {
            Object tempObj;
            if (config.getCellData(i, 0).contains(testCaseName)) {
                int j = 1;
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(CustomAnnotations.ExcelColumn.class)) {
                        CustomAnnotations.ExcelColumn excelColumn = field.getAnnotation(CustomAnnotations.ExcelColumn.class);
                        field.setAccessible(true); // should work on private fields
                        try {
                            if (excelColumn.value() == j) {
                                field.set(obj, config.getCellData(i, j++));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                tempObj = cloner.deepClone(obj);
                recordsList.add(tempObj);
            }
        }
        return recordsList.iterator();
    }

}
