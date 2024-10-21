package com.ca.utilities;

import java.util.ResourceBundle;

public class Constants {


    //***********Data Provider Excel***********/
    /***********Categories***********/
    public static final String CATEGORIES_WORKBOOK = "CategoriesData.xlsx";
    public static final String CATEGORIES_SHEET = "Categories";
    /***********Products***********/
    public static final String PRODUCTS_WORKBOOK = "ProductsData.xlsx";
    public static final String PRODUCTS_SHEET = "Products";
    /***********Stores***********/
    public static final String STORES_WORKBOOK = "StoresData.xlsx";
    public static final String STORES_SHEET = "Stores";
    /***********Services***********/
    public static final String SERVICES_WORKBOOK = "ServicesData.xlsx";
    public static final String SERVICES_SHEET = "Services";


    /***********Environment************/
    private static final ResourceBundle ENVIRONMENT_RN = ResourceBundle.getBundle("environment");
    public static final String ENVIRONMENT_NAME = ENVIRONMENT_RN.getString("env.name");
    public static final String APPLICATION_HOST = ENVIRONMENT_RN.getString("app.host");



}
