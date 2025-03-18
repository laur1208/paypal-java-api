package com.rlaur.paypal.http;

public interface FormParams {

    String key();

    String value();

    final class OfToken implements FormParams {

        @Override
        public String key() {
            return "grant_type";
        }

        @Override
        public String value() {
            return "client_credentials";
        }
    }

}
