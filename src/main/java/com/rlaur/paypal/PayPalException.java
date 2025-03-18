package com.rlaur.paypal;

import javax.json.JsonObject;

public final class PayPalException extends RuntimeException {

    private final String method;

    private final String endpoint;

    private final int status;

    private final JsonObject payload;

    public PayPalException(String method, String endpoint, int status, JsonObject payload) {
        super(
                String.format("Encountered an error while calling %s on %s. Status: %d. Payload: %s",
                        method, endpoint, status, payload)
        );
        this.method = method;
        this.endpoint = endpoint;
        this.status = status;
        this.payload = payload;
    }

    public String method() {
        return this.method;
    }

    public String endpoint() {
        return this.endpoint;
    }

    public int status() {
        return this.status;
    }

    public JsonObject payload() {
        return this.payload;
    }
}
