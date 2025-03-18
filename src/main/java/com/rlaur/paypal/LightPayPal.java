package com.rlaur.paypal;

import com.rlaur.paypal.http.Authentication;
import com.rlaur.paypal.http.ClientCredentialsAuth;
import com.rlaur.paypal.http.JsonResources;
import com.rlaur.paypal.http.RequestHeaders;

import java.net.URI;

public class LightPayPal implements PayPal {

    private final JsonResources client;

    private final URI uri;

    private final Authentication auth;

    public LightPayPal(final String clientId, final String clientSecret) {
        this(new ClientCredentialsAuth(clientId, clientSecret, "https://api-m.sandbox.paypal.com/v1"));
    }

    public LightPayPal(final Authentication auth) {
        this(new JsonResources.FromHttp(auth), URI.create(auth.url()), auth);
    }

    private LightPayPal(final JsonResources client, final URI uri, final Authentication auth) {
        this.client = client;
        this.uri = uri;
        this.auth = auth;
    }

    @Override
    public CatalogProducts catalogProducts() {
        return new PPCatalogProducts(
                new JsonResources.FromHttp(auth).withHeaders(new RequestHeaders(auth))
                , URI.create(uri + "/catalogs")
                , this
        );
    }
}
