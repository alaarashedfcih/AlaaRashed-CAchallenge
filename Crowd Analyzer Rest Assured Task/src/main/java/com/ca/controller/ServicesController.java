package com.ca.controller;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ServicesController class contains methods for interacting with Services-related endpoints.
 * It extends the BaseController class.
 */
public class ServicesController extends BaseController{


    /**
     * Creates a new Service.
     *
     * @return Response object containing the result of the Create Service request.
     */
    public static Response createServiceRequest(String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .post("/services");
    }

    public static Response getSingleServiceRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .get("/services/{id}");
    }

    public static Response updateSingleServiceRequest(String id, String requestBody) {
        return given()
                .spec(baseRequestSpecification)
                .body(requestBody)
                .pathParam("id", id)
                .patch("/services/{id}");
    }

    public static Response deleteSingleServiceRequest(String id) {
        return given()
                .spec(baseRequestSpecification)
                .pathParam("id", id)
                .delete("/services/{id}");
    }

    public static Response getServicesRequest() {
        return given()
                .spec(baseRequestSpecification)
                .get("/services");
    }

    public static Response getServicesWithLimitRequest(int limit) {
        // Send the request to get all Abort Reasons
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$limit", limit)
                .get("/services");
    }

    public static Response getServicesWithSkipRequest(int skip) {
        return given()
                .spec(baseRequestSpecification)
                .queryParam("$skip", skip)
                .get("/services");
    }

}
