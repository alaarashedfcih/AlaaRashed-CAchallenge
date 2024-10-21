package com.ca.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ProductsController class contains methods for interacting with Products-related endpoints.
 * It extends the BaseController class.
 */
public class ProductsController extends BaseController{


    /**
     * Creates a new Product.
     *
     * @return Response object containing the result of the Create Product request.
     */
    public static Response createProductRequest(String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .post("/products");
    }

    public static Response getSingleProductRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .get("/products/{id}");
    }

    public static Response updateSingleProductRequest(String id, String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .pathParam("id", id)
                .patch("/products/{id}");
    }

    public static Response deleteSingleProductRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .delete("/products/{id}");
    }

    public static Response getProductsRequest() {
        return given()
                .spec(baseRequestSpecification)
                .get("/products");
    }

    public static Response getProductsWithLimitRequest(int limit) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$limit", limit)
                .get("/products");
    }

    public static Response getProductsWithSkipRequest(int skip) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$skip", skip)
                .get("/products");
    }

}
