package com.ca.controller;

import com.ca.utilities.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Base controller for handling REST API requests.
 */
public class BaseController {

    static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    public static final RequestSpecification baseRequestSpecification = baseRequestSpecificationBuilder();


    // Request specification for the base URI and common headers
    // relaxedHTTPSValidation generic for all requests
    public static  RequestSpecification baseRequestSpecificationBuilder (){
        return new RequestSpecBuilder()
                .setBaseUri(Constants.APPLICATION_HOST)
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build()
                .relaxedHTTPSValidation();
    }







}
