package com.rlaur.paypal.http;

import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.wire.TrustedWire;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;

/**
 * A JSON representation after calling a HTTP Method
 *
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface JsonResources {

    Resource get(final URI url);

    Resource post(final URI url, final JsonObject body);

    /**
     * Json Resource after calling a HTTP Method
     */
    final class FromHttp implements JsonResources {

        /**
         * Actual headers of the request
         */
        private final Headers headers;

        /**
         * Constructor
         * @param headers Headers
         */
        public FromHttp(Headers headers) {
            this.headers = headers;
        }

        @Override
        public Resource get(URI url) {
            return null;
        }

        @Override
        public Resource post(URI url, JsonObject body) {
            try {
                JsonResponse response = this
                        .prepareRequest(url, "POST", body)
                        .through(TrustedWire.class)
                        .fetch()
                        .as(JsonResponse.class);
                return new JsonResult(
                        response.status(),
                        response.body(),
                        new Headers.OfRequest(response.headers())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private Request prepareRequest(URI url, String method, JsonObject body) {
            final Request[] request = {new JdkRequest(url)};

            request[0] = request[0].method(method);

            if (body != null) {
                request[0] = request[0].body().set(body).back();
            }

            this.headers.iterator().forEachRemaining(
                    header ->
                            request[0] = request[0].header(header.name(), header.value())
            );

            request[0] = request[0].body().formParam("grant_type", "client_credentials").back();

            return request[0];
        }
    }

    /**
     * Implementation of a Resource after calling the Http Request
     */
    final class JsonResult implements Resource {

        /**
         * Status code of the request
         */
        private final int status;

        /**
         * Body of the request
         */
        private final String body;

        /**
         * Headers of the request
         */
        private final Headers headers;

        /**
         * Constructor
         * @param status int status
         * @param body String body
         * @param headers Headers
         */
        public JsonResult(int status, String body, Headers headers) {
            this.status = status;
            this.body = body;
            this.headers = headers;
        }

        @Override
        public int status() {
            return this.status;
        }

        @Override
        public JsonObject json() {
            return Json.createReader(
                    new StringReader(this.body)
            ).readObject();
        }

        @Override
        public JsonArray jsonArray() {
            return Json.createReader(
                    new StringReader(this.body)
            ).readArray();
        }

        @Override
        public Headers headers() {
            return this.headers;
        }
    }

}
