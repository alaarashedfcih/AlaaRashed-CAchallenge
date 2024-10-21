package com.ca.tests.products;

import com.ca.dataproviderobjects.excel.ProductsData;
import com.ca.dataproviderobjects.json.CreateProductRequestBody;
import com.ca.dataproviderobjects.json.UpdateProductRequestBody;
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

import static com.ca.controller.ProductsController.*;
import static com.ca.utilities.Utilities.extractElementFromResponse;
import static org.hamcrest.Matchers.equalTo;

public class CrudSingleProductTest extends BaseTest {
    private ProductsData currentTestData; // Variable to store the current test data
    private static final Logger log = LoggerFactory.getLogger(CrudSingleProductTest.class);
    int createdProductId;

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(Method method, Object[] testData, ITestContext ctx) throws JSONException {
        String testCaseName = ((ProductsData) testData[0]).getTestCaseName();
        ctx.setAttribute(method.getName(), testCaseName.isEmpty() ? "Validate CRUD operations on a new Product" : testCaseName);
        currentTestData = (ProductsData) testData[0];

        String requestBody = CreateProductRequestBody.toJson(
                currentTestData.getName(),
                "one",
                500,
                200,
                "tag",
                "product",
                "factory",
                "nokia",
                "help.com",
                "img"

        );

        //Create a new Product
        Response createProductResponse = createProductRequest(requestBody);
        createProductResponse.then().assertThat().statusCode(Integer.parseInt(currentTestData.getStatusCode()));
        createdProductId = (int) extractElementFromResponse(createProductResponse, "id");
        log.debug(createProductResponse.then().log().all().toString());

        // get the Product category
        Response getSingleProduct = getSingleProductRequest(String.valueOf(createdProductId));
        getSingleProduct.then().assertThat().body("name", equalTo(currentTestData.getName()));
        getSingleProduct.then().assertThat().body("id", equalTo(createdProductId));
        log.debug(getSingleProduct.then().log().all().toString());
    }

    @Test(alwaysRun = true, dataProvider = "ProductsDataFeed", dataProviderClass = DataProviderSource.class)
    public void ValidateSingleProductTest(ProductsData data) {

        String requestBody = UpdateProductRequestBody.toJson(
                data.getNewName()
        );

        // update the created category
        Response updateSingleProductResponse = updateSingleProductRequest(String.valueOf(createdProductId),requestBody);
        updateSingleProductResponse.then().assertThat().statusCode(200);
        log.debug(updateSingleProductResponse.then().log().all().toString());

        // get the updated category
        Response getSingleProduct = getSingleProductRequest(String.valueOf(createdProductId));
        getSingleProduct.then().assertThat().body("name", equalTo(data.getNewName()));

    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (currentTestData != null) {

            // delete the created category
            Response deleteSingleProductResponse = deleteSingleProductRequest(String.valueOf(createdProductId));
            deleteSingleProductResponse.then().assertThat().statusCode(200);
            log.debug(deleteSingleProductResponse.then().log().all().toString());
        }

    }
}
