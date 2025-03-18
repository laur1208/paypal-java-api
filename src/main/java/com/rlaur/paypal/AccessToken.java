package com.rlaur.paypal;

public interface AccessToken {

    String scope();

    String value();

    String type();

    String appId();

    int expiresIn();

    String nonce();

}
