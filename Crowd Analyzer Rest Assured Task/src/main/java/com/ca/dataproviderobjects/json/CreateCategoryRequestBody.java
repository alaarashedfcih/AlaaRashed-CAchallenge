package com.ca.dataproviderobjects.json;

import lombok.Getter;
import lombok.Setter;

import static com.ca.utilities.Utilities.convertObjectToJson;

@Getter
@Setter
public class CreateCategoryRequestBody {

    private String name;
    private String id;


    public static String toJson(String name, String id) {

        CreateCategoryRequestBody requestBody = new CreateCategoryRequestBody();

        requestBody.setName(name);
        requestBody.setId(id);

        // Convert the object to JSON
        return convertObjectToJson(requestBody);

    }


}
