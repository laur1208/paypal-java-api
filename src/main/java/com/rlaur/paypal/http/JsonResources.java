package com.rlaur.paypal.http;

import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.wire.TrustedWire;
import com.rlaur.paypal.PayPalException;

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

    Resource get(final URI uri);

    default Resource post(final URI uri) {
        return this.post(uri, null);
    }

    Resource post(final URI uri, final JsonObject body);

    JsonResources withHeaders(final Headers headers);

    /**
     * Json Resource after calling a HTTP Method
     */
    final class FromHttp implements JsonResources {

        /**
         * Client credentials
         */
        private final Authentication auth;

        /**
         * Actual headers of the request
         */
        private final Headers headers;

        /**
         * Form params of the request
         */
        private final FormParam formParam;


        public FromHttp(final Authentication auth) {
            this(auth, null, null);
        }

        /**
         * Constructor
         *
         * @param headers Headers
         */
        public FromHttp(final Authentication auth, final Headers headers) {
            this(auth, headers, null);
        }

        /**
         * Constructor
         *
         * @param headers   Headers
         * @param formParam FormParams
         */
        public FromHttp(Headers headers, FormParam formParam) {
            this(null, headers, formParam);
        }

        public FromHttp(final Authentication auth, final Headers headers, final FormParam formParam) {
            this.auth = auth;
            this.headers = headers;
            this.formParam = formParam;
        }

        @Override
        public Resource get(URI uri) {
            try {
                JsonResponse response = this
                        .prepareRequest(uri, "GET", null)
                        .through(TrustedWire.class)
                        .fetch()
                        .as(JsonResponse.class);

                return getResource("GET", uri, response);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Resource post(URI uri, JsonObject body) {
            try {
                JsonResponse response = this
                        .prepareRequest(uri, "POST", body)
                        .through(TrustedWire.class)
                        .fetch()
                        .as(JsonResponse.class);

                return getResource("POST", uri, response);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public JsonResources withHeaders(Headers headers) {
            return new JsonResources.FromHttp(this.auth, headers);
        }

        private Request prepareRequest(URI uri, String method, JsonObject body) {
            final Request[] request = {new JdkRequest(uri)};

            request[0] = request[0].method(method);

            if (body != null) {
                request[0] = request[0].body().set(body).back();
            }

            this.headers.iterator().forEachRemaining(
                    header ->
                            request[0] = request[0].header(header.name(), header.value())
            );
            if (this.formParam != null) {
                request[0] = request[0].body().formParam(this.formParam.key(), this.formParam.value()).back();
            }

            return request[0];
        }

        private Resource getResource(String method, URI uri, JsonResponse response) {
            if (response.status() == 200 || response.status() == 201 || response.status() == 202 || response.status() == 204) {
                return new JsonResult(
                        response.status(),
                        response.body(),
                        new RequestHeaders(response.headers())
                );
            }
            throw new PayPalException(
                    method, uri.toString(), response.status(), response.json().readObject()
            );
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
         *
         * @param status  int status
         * @param body    String body
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
