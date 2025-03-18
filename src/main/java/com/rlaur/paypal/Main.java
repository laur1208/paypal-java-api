package com.rlaur.paypal;

import com.rlaur.paypal.http.Header;
import com.rlaur.paypal.http.Headers;
import com.rlaur.paypal.http.JsonResources;
import com.rlaur.paypal.http.Resource;

import javax.json.Json;

import java.net.URI;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

//        Headers headers = new Headers.OfRequest(
//                () -> Stream.of(
//                        new Header.General("Content-Type", "application/json"),
//                        new Header.General("Accept", "application/json"),
//                        new Header.Authorization(System.getenv(Env.CLIENT_ID), System.getenv(Env.CLIENT_SECRET))
//                )
//        );

//        Resource resource = new JsonResources.FromHttp(
//               headers
//        ).post(
//                URI.create(System.getenv(Env.BASE_URL) + "/oauth2/token")
//        );
//        System.out.println(resource.json());

//        AccessToken token = new Token();
//        System.out.println(token.value());
//        System.out.println(token.expiresIn());

        PayPal payPal = new LightPayPal("https://api-m.sandbox.paypal.com/v1");

        CatalogProduct product = payPal
                .login("1", "2")
                .catalogProducts()
                .create(
                        Json.createObjectBuilder()
//                                .add("id", "112312317")
                                .add("name", "lala")
                                .add("description", "T-Shirt")
                                .add("type", "PHYSICAL")
                                .add("category", "CLOTHING")
                                .add("home_url", "https://www.example.com")
                                .add("image_url", "https://www.example.com/t-shirt.jpg")
                                .build()
                );

        System.out.println(product.id());
        System.out.println(product.category());
        System.out.println(product.imageUrl());

    }
}
