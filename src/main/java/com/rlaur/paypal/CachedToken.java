package com.rlaur.paypal;

import com.rlaur.paypal.http.Authentication;
import com.rlaur.paypal.http.ClientCredentialsAuth;

import java.time.LocalDateTime;

public class CachedToken implements Authentication {

    private final Authentication auth;

    public CachedToken(Authentication auth) {
        this.auth = auth;
    }

    @Override
    public String clientId() {
        return this.cached().clientId();
    }

    @Override
    public String clientSecret() {
        return this.cached().clientSecret();
    }

    @Override
    public String url() {
        return this.cached().url();
    }

    @Override
    public AccessToken token() {
        return this.cached().token();
    }

    private Authentication cached() {
        final AccessToken token = this.auth.token();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusSeconds(token.expiresIn());
//        System.out.println(expirationDateTime + " - " + token.value());
        if(!token.value().equals("A21AAKkY9dEuSS8MndAnwj9OrFVVwEXgSH-AvJWVOd51lrQJ0w2ocuWv0gti5pnVApCRJDuTbVa6cv663jpJ1C-NjDPkfKJ6Q")){
            throw new IllegalArgumentException("QQQ");
        }
        if(LocalDateTime.now().isAfter(expirationDateTime)) {
            return new ClientCredentialsAuth(this.auth.clientId(), this.auth.clientSecret(), this.url());
        } else {
            return this.auth;
        }
    }
}
