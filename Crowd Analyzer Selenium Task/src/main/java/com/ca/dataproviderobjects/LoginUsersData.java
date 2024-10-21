package com.ca.dataproviderobjects;

import com.ca.utilities.CustomAnnotations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsersData {

    @CustomAnnotations.ExcelColumn(1)
    String testCaseName;

    @CustomAnnotations.ExcelColumn(2)
    String userName;

    @CustomAnnotations.ExcelColumn(3)
    String password;

    @CustomAnnotations.ExcelColumn(4)
    String errorMessage;

    @CustomAnnotations.ExcelColumn(5)
    String firstName;

    @CustomAnnotations.ExcelColumn(6)
    String lastName;

    @CustomAnnotations.ExcelColumn(7)
    String postalCode;

    @CustomAnnotations.ExcelColumn(8)
    String dogCount;

    @CustomAnnotations.ExcelColumn(9)
    String catCount;


}

