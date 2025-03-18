package com.rlaur.paypal.http;

import com.rlaur.paypal.CachedToken;
import com.rlaur.paypal.Token;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RequestHeaders implements Headers {

    private final Supplier<Stream<Header>> headers;

    public RequestHeaders() {
        this(
                new Header.General("Content-Type", "application/json"),
                new Header.General("Accept", "application/json"),
                new Header.Bearer(new CachedToken(new Token())),
                new Header.Prefer("representation")
        );
    }

    public RequestHeaders(Map<String, List<String>> headers) {
        this(
                () -> headers.entrySet()
                        .stream()
                        .flatMap(
                                entry -> entry.getValue().stream()
                                        .map(value -> new Header.General(entry.getKey(), value))
                        )

        );
    }

    public RequestHeaders(Header... headers) {
        this(
                () -> Stream.of(headers)
        );
    }

    public RequestHeaders(Supplier<Stream<Header>> headers) {
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
