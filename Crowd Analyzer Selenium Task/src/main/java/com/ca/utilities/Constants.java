package com.ca.utilities;

import java.util.ResourceBundle;

public class Constants {

    private static final ResourceBundle ENVIRONMENT_RN = ResourceBundle.getBundle("environment");
    public static final String ENVIRONMENT_NAME = ENVIRONMENT_RN.getString("env.name");
    public static final String APPLICATION_HOST = ENVIRONMENT_RN.getString("app.host");
    public static final String PASS_SCREENSHOTS_FLAG = ENVIRONMENT_RN.getString("pass.screenshots.flag");


    /***********Data Provider Excel***********/
    public static final String LOGIN_USERS_WORKBOOK = "LoginUsersData.xlsx";
    public static final String LOGIN_USERS_SHEET = "LoginUsersData";

}
