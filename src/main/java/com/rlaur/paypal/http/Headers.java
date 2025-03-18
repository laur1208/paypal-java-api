package com.rlaur.paypal.http;

import com.rlaur.paypal.CachedToken;
import com.rlaur.paypal.Token;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Header Parameters of a Http REST call.
 *
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface Headers extends Iterable<Header> {

    /**
     * Set a specific header on a Http call.
     *
     * @param header Parameter
     * @return Headers that were set
     */
    Headers set(Header header);


    /**
     * Base implementation of Headers of a request
     */
    final class OfRequest implements Headers {

        private final Supplier<Stream<Header>> headers;

        public OfRequest(Map<String, List<String>> headers) {
            this(
                    () -> headers.entrySet()
                            .stream()
                            .flatMap(
                                    entry -> entry.getValue().stream()
                                            .map(value -> new Header.General(entry.getKey(), value))
                            )

            );
        }

        public OfRequest(Header... headers) {
            this(
                    () -> Stream.of(headers)
            );
        }

        /**
         * Constructor
         *
         * @param header Header
         */
        public OfRequest(Header header) {
            this.headers = () -> Stream.of(header);
        }


        /**
         * Constructor
         *
         * @param headers Supplier Headers
         */
        public OfRequest(Supplier<Stream<Header>> headers) {
            this.headers = headers;
        }

        @Override
        public Headers set(Header header) {
            return new Headers.OfRequest(
                    () -> Stream.concat(this.headers.get(), Stream.of(header))
            );
        }

        @Override
        public Iterator<Header> iterator() {
            return this.headers.get().iterator();
        }
    }

    final class OfResource implements Headers {

        private final Headers headers;

        public OfResource() {
            this(
                    new Headers.OfRequest(
                            new Header.General("Content-Type", "application/json"),
                            new Header.General("Accept", "application/json"),
                            new Header.Bearer(new CachedToken(new Token())),
                            new Header.Prefer("representation")

                    )
            );
        }

        public OfResource(Headers headers) {
            this.headers = headers;
        }

        @Override
        public Headers set(Header header) {
            return null;
        }

        @Override
        public Iterator<Header> iterator() {
            return this.headers.iterator();
        }
    }

//    final class OfToken extends OfRequest {
//
//        public OfToken(Supplier<Stream<Header>> headers) {
//            super(headers);
//        }
//
//        @Override
//        public Headers set(Header header) {
//            return new Headers.OfToken(
//                    () -> Stream.concat(super.headers.get(), Stream.of(header))
//            );
//        }
//    }

}
