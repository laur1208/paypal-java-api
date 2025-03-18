package com.rlaur.paypal;

import com.rlaur.paypal.http.*;

import javax.json.JsonObject;
import java.net.URI;
import java.util.stream.Stream;

/**
 * Token of the PayPal API
 */
public class Token implements AccessToken {

    /**
     * Token resource
     */
    private Resource resource;

    public Token() {
        this(
                new JsonResources.FromHttp(
                        new Headers.OfRequest(
                                () -> Stream.of(
                                        new Header.General("Content-Type", "application/json"),
                                        new Header.General("Accept", "application/json"),
                                        new Header.Authorization(System.getenv(Env.CLIENT_ID), System.getenv(Env.CLIENT_SECRET))
                                )
                        ),
                        new FormParams.OfToken()
                ).post(URI.create(System.getenv(Env.BASE_URL) + "/oauth2/token"))
        );
    }

    private Token(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String scope() {
        return resource.json().getString("scope");
    }

    @Override
    public String value() {
        return resource.json().getString("access_token");
    }

    @Override
    public String type() {
        return resource.json().getString("token_type");
    }

    @Override
    public String appId() {
        return resource.json().getString("app_id");
    }

    @Override
    public int expiresIn() {
        return resource.json().getInt("expires_in");
    }

    @Override
    public String nonce() {
        return resource.json().getString("nonce");
    }

    private JsonObject json() {
        if(this.resource == null) {
            throw new IllegalStateException("Token resource is not available");
        }
        return this.resource.json();
    }
}
