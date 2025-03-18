package com.rlaur.paypal;

import javax.json.Json;

public class Main {
    public static void main(String[] args) {

        PayPal payPal = new LightPayPal(System.getenv(Env.CLIENT_ID), System.getenv(Env.CLIENT_SECRET));

        CatalogProduct product = payPal
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


        CatalogProduct p = payPal.catalogProducts().get("PROD-6CJ66407YA064270W");
        System.out.println(p.name());

    }
}
