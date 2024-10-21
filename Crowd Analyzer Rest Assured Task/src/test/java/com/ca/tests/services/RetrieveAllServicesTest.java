package com.ca.tests.services;

import com.ca.dataproviderobjects.excel.ServicesData;
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
import static com.ca.controller.ServicesController.*;
import static com.ca.utilities.Utilities.responseToJson;

public class RetrieveAllServicesTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(RetrieveAllServicesTest.class);
    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((ServicesData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate Retrieve all Services" : testCaseName);
    }

    @Test(alwaysRun = true, dataProvider = "ServicesDataFeed", dataProviderClass = DataProviderSource.class)
    public void getAllServicesTest(ServicesData data) {

        // get all Services
        Response getServicesResponse = getServicesRequest();
        getServicesResponse.then().assertThat().statusCode(200);
        int total = getServicesResponse.path("total");
        Assert.assertTrue(total > 0, "Total count is not greater than 0!");


        JsonPath jsonPath = responseToJson(getServicesResponse);
        // Assert that each Service in the 'data' list has a 'name' and an 'id'

        jsonPath.getList("data").forEach(service -> {
            String serviceName = ((Map<String, Object>) service).get("name").toString();
            String serviceId = ((Map<String, Object>) service).get("id").toString();

            softAssert.assertNotNull(serviceName, "Service name should not be null");
            softAssert.assertFalse(serviceName.isEmpty(), "Service name should not be empty");
            softAssert.assertNotNull(serviceId, "Service id should not be null");
            softAssert.assertFalse(serviceId.isEmpty(), "Service id should not be empty");
        });

        // get all Services with limit
        Response getServicesWithLimitResponse = getServicesWithLimitRequest(Integer.parseInt(data.getLimit()));
        getServicesWithLimitResponse.then().assertThat().statusCode(200);
        int limit = getServicesWithLimitResponse.path("limit");
        Assert.assertEquals(limit, Integer.parseInt(data.getLimit()), "Limit is not 2!");

        // get all Services with skip
        Response getServicesWithSkipResponse = getServicesWithSkipRequest(Integer.parseInt(data.getSkip()));
        getServicesWithSkipResponse.then().assertThat().statusCode(200);
        int skip = getServicesWithSkipResponse.path("skip");
        Assert.assertEquals(skip, Integer.parseInt(data.getSkip()), "Skip is 0!");

        log.debug(getServicesWithSkipResponse.then().log().all().toString());
        softAssert.assertAll();


    }
}
