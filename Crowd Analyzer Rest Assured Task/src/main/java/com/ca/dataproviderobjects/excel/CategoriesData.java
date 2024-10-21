package com.ca.dataproviderobjects.excel;

import com.ca.utilities.CustomAnnotations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriesData {

    @CustomAnnotations.ExcelColumn(1)
    String testCaseName;

    @CustomAnnotations.ExcelColumn(2)
    String limit;

    @CustomAnnotations.ExcelColumn(3)
    String skip;

    @CustomAnnotations.ExcelColumn(4)
    String id;

    @CustomAnnotations.ExcelColumn(5)
    String name;

    @CustomAnnotations.ExcelColumn(6)
    String newName;

    @CustomAnnotations.ExcelColumn(7)
    String statusCode;

}

