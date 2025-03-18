package com.rlaur.paypal.http;

import com.rlaur.paypal.Env;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TokenHeaders implements Headers {

    private final Supplier<Stream<Header>> headers;

    public TokenHeaders() {
        this(
                new Header.General("Content-Type", "application/json"),
                new Header.General("Accept", "application/json"),
                new Header.Authorization(System.getenv(Env.CLIENT_ID), System.getenv(Env.CLIENT_SECRET))
        );
    }

    public TokenHeaders(Header... headers) {
        this(
                () -> Stream.of(headers)
        );
    }

    public TokenHeaders(Supplier<Stream<Header>> headers) {
        this.headers = headers;
    }

    @Override
    public Headers set(Header header) {
        return null;
    }

    @Override
    public Iterator<Header> iterator() {
        return this.headers.get().iterator();
    }
}
