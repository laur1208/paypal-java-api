package com.rlaur.paypal;

/**
 * PayPal API highest abstraction. It is the entry point of the application
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface PayPal {

    /**
     * User of the API. It may be personal or business user
     * @return User
     */
    User login(final String clientId, final String clientSecret);

}
