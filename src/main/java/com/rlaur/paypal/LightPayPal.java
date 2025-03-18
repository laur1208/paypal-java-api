package com.rlaur.paypal;

import java.net.URI;

public class LightPayPal implements PayPal{

    private final URI uri;

    public LightPayPal(String uri){
        this(URI.create(uri));
    }

    private LightPayPal(URI uri) {
        this.uri = uri;
    }

    @Override
    public User login(String clientId, String clientSecret) {
        return new PPUser(uri, this);
    }
}
