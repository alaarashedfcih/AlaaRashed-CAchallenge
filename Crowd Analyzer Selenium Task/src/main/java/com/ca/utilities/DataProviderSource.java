package com.ca.utilities;

import com.ca.dataproviderobjects.LoginUsersData;
import com.ca.utilities.CustomAnnotations.ExcelColumn;
import com.rits.cloning.Cloner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class DataProviderSource {
    private static final Logger logger = LoggerFactory.getLogger(DataProviderSource.class);


    /***************************Login Users Data****************************************/
    @DataProvider(name = "LoginUsersDataFeed")
    public Iterator<Object> loginUsersDataFeed(Method method) {
        String testCaseName = method.getName();
        LoginUsersData loginLogOutUsersData = new LoginUsersData();
        return fetchDataFromSheet(loginLogOutUsersData, Constants.LOGIN_USERS_WORKBOOK,
                Constants.LOGIN_USERS_SHEET, testCaseName);
    }


    /***************************Fetch Data From Excel Sheet****************************************/
    public Iterator<Object> fetchDataFromSheet(Object obj, String workBookName, String sheetName, String testCaseName) {
        ExcelUtils config = new ExcelUtils();
        config.setTestDataExcelPath("src/main/resources/testdata/" + workBookName);
        config.setExcelFileSheet(sheetName);

        int lastRow = config.getLastRow();
        List<Object> recordsList = new ArrayList<>();
        Cloner cloner = new Cloner();

        for (int i = 1; i < lastRow; i++) {
            if (config.getCellData(i, 0).contains(testCaseName)) {
                populateFieldsFromExcelRow(obj, config, i);
                Object clonedObj = cloner.deepClone(obj);
                recordsList.add(clonedObj);
            }
        }
        return recordsList.iterator();
    }

    private void populateFieldsFromExcelRow(Object obj, ExcelUtils config, int rowIndex) {
        Field[] fields = obj.getClass().getDeclaredFields();
        int cellIndex = 1; // Start from column 1 since column 0 is reserved for the test case name

        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                try {
                    if (excelColumn.value() == cellIndex) {
                        field.setAccessible(true);
                        Optional<String> cellData = Optional.ofNullable(config.getCellData(rowIndex, cellIndex++));
                        field.set(obj, cellData.orElse(""));
                    }
                } catch (IllegalAccessException e) {
                    logger.error("Error setting field value for field: {}", field.getName(), e);
                }
            }
        }
    }

}
