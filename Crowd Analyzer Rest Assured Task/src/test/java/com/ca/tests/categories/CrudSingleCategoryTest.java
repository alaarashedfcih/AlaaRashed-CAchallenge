package com.ca.tests.categories;

import com.ca.dataproviderobjects.excel.CategoriesData;
import com.ca.dataproviderobjects.json.CreateCategoryRequestBody;
import com.ca.dataproviderobjects.json.UpdateCategoryRequestBody;
import com.ca.tests.BaseTest;
import com.ca.utilities.DataProviderSource;
import io.restassured.response.Response;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.ca.controller.CategoriesController.*;
import static org.hamcrest.Matchers.equalTo;

public class CrudSingleCategoryTest extends BaseTest {
    private CategoriesData currentTestData; // Variable to store the current test data
    private static final Logger log = LoggerFactory.getLogger(CrudSingleCategoryTest.class);

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((CategoriesData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate CRUD operations on a new category" : testCaseName);
        currentTestData = (CategoriesData) testData[0];

        String requestBody = CreateCategoryRequestBody.toJson(
                currentTestData.getName(),
                currentTestData.getId()
        );

        //Create a new category
        Response createCategoryResponse = createCategoryRequest(requestBody);
        createCategoryResponse.then().assertThat().statusCode(Integer.parseInt(currentTestData.getStatusCode()));
        log.debug(createCategoryResponse.then().log().all().toString());

        // get the created category
        Response getSingleCategory = getSingleCategoryRequest(currentTestData.getId());
        getSingleCategory.then().assertThat().body("name", equalTo(currentTestData.getName()));
        getSingleCategory.then().assertThat().body("id", equalTo(currentTestData.getId()));
        log.debug(getSingleCategory.then().log().all().toString());
    }

    @Test(alwaysRun = true, dataProvider = "CategoriesDataFeed", dataProviderClass = DataProviderSource.class)
    public void ValidateSingleCategoryTest(CategoriesData data) {

        String requestBody = UpdateCategoryRequestBody.toJson(
                data.getNewName()
        );

        // update the created category
        Response updateSingleCategoryResponse = updateSingleCategoryRequest(data.getId(),requestBody);
        updateSingleCategoryResponse.then().assertThat().statusCode(200);
        log.debug(updateSingleCategoryResponse.then().log().all().toString());

        // get the updated category
        Response getSingleCategory = getSingleCategoryRequest(data.getId());
        getSingleCategory.then().assertThat().body("name", equalTo(data.getNewName()));

    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (currentTestData != null) {

            // delete the created category
            Response deleteSingleCategoryResponse = deleteSingleCategoryRequest(currentTestData.getId());
            deleteSingleCategoryResponse.then().assertThat().statusCode(200);
            log.debug(deleteSingleCategoryResponse.then().log().all().toString());
        }

    }
}
