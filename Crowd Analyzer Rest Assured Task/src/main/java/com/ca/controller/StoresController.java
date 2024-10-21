package com.ca.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * StoresController class contains methods for interacting with Stores-related endpoints.
 * It extends the BaseController class.
 */
public class StoresController extends BaseController{


    /**
     * Creates a new Store.
     *
     * @return Response object containing the result of the Create Store request.
     */
    public static Response createStoreRequest(String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .post("/stores");
    }

    public static Response getSingleStoreRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .get("/stores/{id}");
    }

    public static Response updateSingleStoreRequest(String id, String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .pathParam("id", id)
                .patch("/stores/{id}");
    }

    public static Response deleteSingleStoreRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .delete("/stores/{id}");
    }

    public static Response getStoresRequest() {
        return given()
                .spec(baseRequestSpecification)
                .get("/stores");
    }

    public static Response getStoresWithLimitRequest(int limit) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$limit", limit)
                .get("/stores");
    }

    public static Response getStoresWithSkipRequest(int skip) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$skip", skip)
                .get("/stores");
    }

}
