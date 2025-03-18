package com.rlaur.paypal.http;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Header Parameters of a Http REST call.
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface Headers extends Iterable<Header> {

    /**
     * Set a specific header on a Http call.
     * @param header Parameter
     * @return Headers that were set
     */
    Headers set(Header header);

}
