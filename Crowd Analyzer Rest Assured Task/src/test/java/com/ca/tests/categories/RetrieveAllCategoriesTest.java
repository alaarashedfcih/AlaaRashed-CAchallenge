package com.ca.tests.categories;

import com.ca.dataproviderobjects.excel.CategoriesData;
import com.ca.tests.BaseTest;
import com.ca.utilities.DataProviderSource;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Map;

import static com.ca.controller.CategoriesController.*;
import static com.ca.utilities.Utilities.responseToJson;

public class RetrieveAllCategoriesTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(RetrieveAllCategoriesTest.class);
    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((CategoriesData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate Retrieve all categories" : testCaseName);
    }
    @Test(alwaysRun = true, dataProvider = "CategoriesDataFeed", dataProviderClass = DataProviderSource.class)
    public void getAllCategoriesTest(CategoriesData data) {

        // get all categories
        Response getCategoriesResponse = getCategoriesRequest();
        getCategoriesResponse.then().assertThat().statusCode(200);
        int total = getCategoriesResponse.path("total");
        Assert.assertTrue(total > 0, "Total count is not greater than 0!");


        JsonPath jsonPath = responseToJson(getCategoriesResponse);
        // Assert that each category in the 'data' list has a 'name' and an 'id'
        jsonPath.getList("data").forEach(category -> {
            String categoryName = ((Map<String, Object>) category).get("name").toString();
            String categoryId = ((Map<String, Object>) category).get("id").toString();

            softAssert.assertNotNull(categoryName, "Category name should not be null");
            softAssert.assertFalse(categoryName.isEmpty(), "Category name should not be empty");
            softAssert.assertNotNull(categoryId, "Category id should not be null");
            softAssert.assertFalse(categoryId.isEmpty(), "Category id should not be empty");
        });

        // get all categories with limit
        Response getCategoriesWithLimitResponse = getCategoriesWithLimitRequest(Integer.parseInt(data.getLimit()));
        getCategoriesWithLimitResponse.then().assertThat().statusCode(200);
        int limit = getCategoriesWithLimitResponse.path("limit");
        Assert.assertEquals(limit, Integer.parseInt(data.getLimit()), "Limit is not 2!");

        // get all categories with skip
        Response getCategoriesWithSkipResponse = getCategoriesWithSkipRequest(Integer.parseInt(data.getSkip()));
        getCategoriesWithSkipResponse.then().assertThat().statusCode(200);
        int skip = getCategoriesWithSkipResponse.path("skip");
        Assert.assertEquals(skip, Integer.parseInt(data.getSkip()), "Skip is not 0!");

        log.debug(getCategoriesWithLimitResponse.then().log().all().toString());
        softAssert.assertAll();




    }
}
