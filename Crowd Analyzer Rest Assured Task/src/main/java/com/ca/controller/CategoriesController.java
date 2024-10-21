package com.ca.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * CategoriesController class contains methods for interacting with Categories-related endpoints.
 * It extends the BaseController class.
 */
public class CategoriesController extends BaseController{


    /**
     * Creates a new category.
     *
     * @return Response object containing the result of the Create Category request.
     */
    public static Response createCategoryRequest(String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .post("/categories");
    }

    public static Response getSingleCategoryRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .get("/categories/{id}");
    }

    public static Response updateSingleCategoryRequest(String id, String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .pathParam("id", id)
                .patch("/categories/{id}");
    }

    public static Response deleteSingleCategoryRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .delete("/categories/{id}");
    }

    public static Response getCategoriesRequest() {
        return given()
                .spec(baseRequestSpecification)
                .get("/categories");
    }

    public static Response getCategoriesWithLimitRequest(int limit) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$limit", limit)
                .get("/categories");
    }

    public static Response getCategoriesWithSkipRequest(int skip) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$skip", skip)
                .get("/categories");
    }

}
