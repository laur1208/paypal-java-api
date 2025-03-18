package com.rlaur.paypal.http;

public interface FormParam {

    String key();

    String value();

    final class OfToken implements FormParam {

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
