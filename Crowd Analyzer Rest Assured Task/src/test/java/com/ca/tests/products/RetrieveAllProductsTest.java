package com.ca.tests.products;

import com.ca.dataproviderobjects.excel.ProductsData;
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

import static com.ca.controller.ProductsController.*;
import static com.ca.utilities.Utilities.responseToJson;

public class RetrieveAllProductsTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(RetrieveAllProductsTest.class);
    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((ProductsData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate Retrieve all products" : testCaseName);
    }
    @Test(alwaysRun = true, dataProvider = "ProductsDataFeed", dataProviderClass = DataProviderSource.class)
    public void getAllProductsTest(ProductsData data) {

        // get all Products
        Response getProductsResponse = getProductsRequest();
        getProductsResponse.then().assertThat().statusCode(200);
        int total = getProductsResponse.path("total");
        Assert.assertTrue(total > 0, "Total count is not greater than 0!");


        JsonPath jsonPath = responseToJson(getProductsResponse);
        // Assert that each product in the 'data' list has a 'name' and an 'id'
        jsonPath.getList("data").forEach(product -> {
            String productName = ((Map<String, Object>) product).get("name").toString();
            String productId = ((Map<String, Object>) product).get("id").toString();

            softAssert.assertNotNull(productName, "Product name should not be null");
            softAssert.assertFalse(productName.isEmpty(), "Product name should not be empty");
            softAssert.assertNotNull(productId, "Product id should not be null");
            softAssert.assertFalse(productId.isEmpty(), "Product id should not be empty");
        });

        // get all products with limit
        Response getProductsWithLimitResponse = getProductsWithLimitRequest(Integer.parseInt(data.getLimit()));
        getProductsWithLimitResponse.then().assertThat().statusCode(200);
        int limit = getProductsWithLimitResponse.path("limit");
        Assert.assertEquals(limit, Integer.parseInt(data.getLimit()), "Limit is not 2!");

        // get all products with skip
        Response getProductsWithSkipResponse = getProductsWithSkipRequest(Integer.parseInt(data.getSkip()));
        getProductsWithSkipResponse.then().assertThat().statusCode(200);
        int skip = getProductsWithSkipResponse.path("skip");
        Assert.assertEquals(skip, Integer.parseInt(data.getSkip()), "Skip is not 0!");

        log.debug(getProductsWithSkipResponse.then().log().all().toString());
        softAssert.assertAll();




    }
}
