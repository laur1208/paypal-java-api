package com.rlaur.paypal.http;

import com.rlaur.paypal.AccessToken;

public interface Authentication {

    String clientId();

    String clientSecret();

    String url();

    AccessToken token();
}
