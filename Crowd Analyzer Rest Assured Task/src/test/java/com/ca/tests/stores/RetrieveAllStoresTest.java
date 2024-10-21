package com.ca.tests.stores;

import com.ca.dataproviderobjects.excel.ProductsData;
import com.ca.dataproviderobjects.excel.StoresData;
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
import static com.ca.controller.StoresController.*;
import static com.ca.utilities.Utilities.responseToJson;

public class RetrieveAllStoresTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(RetrieveAllStoresTest.class);
    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((StoresData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate Retrieve all Stores" : testCaseName);
    }
    @Test(alwaysRun = true, dataProvider = "StoresDataFeed", dataProviderClass = DataProviderSource.class)
    public void getAllStoresTest(StoresData data) {

        // get all Products
        Response getStoresResponse = getStoresRequest();
        getStoresResponse.then().assertThat().statusCode(Integer.parseInt(data.getStatusCode()));
        int total = getStoresResponse.path("total");
        Assert.assertTrue(total > 0, "Total count is not greater than 0!");


        JsonPath jsonPath = responseToJson(getStoresResponse);
        // Assert that each product in the 'data' list has a 'name' and an 'id'
        jsonPath.getList("data").forEach(product -> {
            String storeName = ((Map<String, Object>) product).get("name").toString();
            String storeId = ((Map<String, Object>) product).get("id").toString();

            softAssert.assertNotNull(storeName, "Store name should not be null");
            softAssert.assertFalse(storeName.isEmpty(), "Store name should not be empty");
            softAssert.assertNotNull(storeId, "Store id should not be null");
            softAssert.assertFalse(storeId.isEmpty(), "Store id should not be empty");
        });

        // get all Stores with limit
        Response getStoresWithLimitResponse = getStoresWithLimitRequest(Integer.parseInt(data.getLimit()));
        getStoresWithLimitResponse.then().assertThat().statusCode(Integer.parseInt(data.getStatusCode()));
        int limit = getStoresWithLimitResponse.path("limit");
        Assert.assertEquals(limit, Integer.parseInt(data.getLimit()), "Limit is not 2!");

        // get all products with skip
        Response getStoresWithSkipResponse = getStoresWithSkipRequest(Integer.parseInt(data.getSkip()));
        getStoresWithSkipResponse.then().assertThat().statusCode(Integer.parseInt(data.getStatusCode()));
        int skip = getStoresWithSkipResponse.path("skip");
        Assert.assertEquals(skip, Integer.parseInt(data.getSkip()), "Skip is not 0!");

        log.debug(getStoresWithSkipResponse.then().log().all().toString());
        softAssert.assertAll();




    }
}
