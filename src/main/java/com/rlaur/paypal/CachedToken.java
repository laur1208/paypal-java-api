package com.rlaur.paypal;

public class CachedToken implements AccessToken{

    private final AccessToken token;

    public CachedToken(AccessToken token) {
        this.token = token;
    }

    @Override
    public String scope() {
        return cached().scope();
    }

    @Override
    public String value() {
        return cached().value();
    }

    @Override
    public String type() {
        return cached().type();
    }

    @Override
    public String appId() {
        return cached().appId();
    }

    @Override
    public int expiresIn() {
        return cached().expiresIn();
    }

    @Override
    public String nonce() {
        return cached().nonce();
    }

    private AccessToken cached() {
        System.out.println("Token expires in " + this.token.expiresIn());
        if(this.token.expiresIn() > 0) {
            return this.token;
        } else {
            return new Token();
        }
    }
}
