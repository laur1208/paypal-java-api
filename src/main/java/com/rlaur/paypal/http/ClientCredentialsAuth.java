package com.rlaur.paypal.http;

import com.rlaur.paypal.AccessToken;
import com.rlaur.paypal.Token;

public class ClientCredentialsAuth implements Authentication {

    private final String clientId;

    private final String clientSecret;

    private final String url;

    private AccessToken accessToken;

    public ClientCredentialsAuth(final String clientId, final String clientSecret, final String url) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
        this.accessToken = null;
    }

    @Override
    public String clientId() {
        return this.clientId;
    }

    @Override
    public String clientSecret() {
        return this.clientSecret;
    }

    @Override
    public String url() {
        return this.url;
    }

    @Override
    public AccessToken token() {
        if(this.accessToken == null){
            this.accessToken = new Token(this);
        }
        return this.accessToken;
    }
}
