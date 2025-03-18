package com.rlaur.paypal;

/**
 * PayPal API highest abstraction. It is the entry point of the application
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface PayPal {

    /**
     * CatalogProducts API.
     * @return CatalogProducts
     */
    CatalogProducts catalogProducts();

}
