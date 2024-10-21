package com.ca.dataproviderobjects.json;

import lombok.Getter;
import lombok.Setter;

import static com.ca.utilities.Utilities.convertObjectToJson;

@Getter
@Setter
public class CreateProductRequestBody {

    private String name;
    private String type;
    private int price;
    private int shipping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;


    public static String toJson(String name, String type, int price, int shipping, String upc,
                                String description, String manufacturer, String model, String url, String image) {

        CreateProductRequestBody requestBody = new CreateProductRequestBody();

        requestBody.setName(name);
        requestBody.setType(type);
        requestBody.setPrice(price);
        requestBody.setShipping(shipping);
        requestBody.setUpc(upc);
        requestBody.setDescription(description);
        requestBody.setManufacturer(manufacturer);
        requestBody.setModel(model);
        requestBody.setUrl(url);
        requestBody.setImage(image);

        // Convert the object to JSON
        return convertObjectToJson(requestBody);

    }


}
