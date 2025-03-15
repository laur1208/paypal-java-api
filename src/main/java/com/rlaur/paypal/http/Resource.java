package com.rlaur.paypal.http;


import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * A resource from calling PayPal's REST API.
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface Resource {

    /**
     * The HTTP Status code of the API call.
     * @return int status
     */
    int status();

    /**
     * The result of the API call as Json.
     * @return JsonObject entity.
     */
    JsonObject json();

    /**
     * The result of the API call as JsonArray.
     * @return JsonArray entity.
     */
    JsonArray jsonArray();

    /**
     * Headers of the Http Call.
     * @return Headers
     */
    Headers headers();

}
