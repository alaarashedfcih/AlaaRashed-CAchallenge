package com.ca.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Random;

/**
 * The Utilities class contains utility methods for common tasks and operations.
 * It provides various helper methods that can be used across the application.
 * These utility methods are designed to improve code organization and usability.
 */
public class Utilities {


    /**
     * Convert the API response to a JSON object.
     *
     * @param response The API response.
     * @return The JSON object.
     */
    public static JsonPath responseToJson(Response response) {
        return new JsonPath(response.asString());
    }

    /**
     * Generate a random integer label between the specified minimum and maximum values.
     *
     * @param min The minimum integer value.
     * @param max The maximum integer value.
     * @return A randomly generated label.
     */
    public static int generateRandomLabel(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Helper method to convert an object to JSON using Jackson ObjectMapper
    public static String convertObjectToJson(Object object) {
        try {
            // Converting a Java class object to a JSON payload as string
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON: " + e.getMessage());
        }
    }


    /**
     * Extracts the element from the response using the provided elementPath.
     *
     * @param response Response of the API call
     * @param elementPath Path to the element in the response
     * @return Value of the extracted element
     */

    public static Object extractElementFromResponse(Response response, String elementPath) {
        return response.then().extract().path(elementPath);
    }


}
