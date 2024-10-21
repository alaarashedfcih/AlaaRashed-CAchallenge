package com.ca.dataproviderobjects.json;

import lombok.Getter;
import lombok.Setter;

import static com.ca.utilities.Utilities.convertObjectToJson;

@Getter
@Setter
public class UpdateCategoryRequestBody {
    private String name;

    public static String toJson(String name) {
        UpdateCategoryRequestBody requestBody = new UpdateCategoryRequestBody();
        requestBody.setName(name);
        // Convert the object to JSON
        return convertObjectToJson(requestBody);
    }

}
