package com.rlaur.paypal.http;

import com.rlaur.paypal.AccessToken;

import java.util.Base64;
import java.util.function.Supplier;

/**
 * A specific header of the Http resource
 *
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface Header {

    /**
     * The name of the header.
     *
     * @return String name
     */
    String name();

    /**
     * The value of the header.
     *
     * @return String value
     */
    String value();


    /**
     * Base implementation of a PayPal Header
     */
    abstract class OfPayPal implements Header {

        /**
         * The name of the header
         */
        private final String name;

        /**
         * The name of the header
         */
        private final String value;

        /**
         * Constructor
         *
         * @param name  String name
         * @param value String value
         */
        public OfPayPal(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public String value() {
            return this.value;
        }
    }

    final class General extends OfPayPal {
        public General(String name, String value) {
            super(name, value);
        }
    }

    final class Prefer extends OfPayPal {
        public Prefer(String representation) {
            super("Prefer", "return=" + representation);
        }
    }

    final class RequestId extends OfPayPal {
        public RequestId(String requestId) {
            super("PayPal-Request-Id", requestId);
        }
    }

    final class Authorization extends OfPayPal {
        public Authorization(String clientId, String clientSecret) {
            super("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        }
    }

    final class Bearer extends OfPayPal {
        public Bearer(Authentication auth) {
            super("Authorization", "Bearer " + auth.token().value());
        }
    }

}
