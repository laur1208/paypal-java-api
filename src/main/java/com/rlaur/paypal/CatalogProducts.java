package com.rlaur.paypal;

import javax.json.JsonObject;

/**
 * Catalog Products API. This is also an Iterable over all merchants products.
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 */
public interface CatalogProducts extends Iterable<CatalogProduct>{

    /**
     * Create a product.
     * @param catalogProduct The product to be created, in Json format.
     * @return CatalogProduct.
     */
    CatalogProduct create(JsonObject catalogProduct);

    /**
     * Get a catalog product by its id.
     * @param id String id of the product.
     * @return {@link CatalogProduct}
     */
    CatalogProduct get(String id);

}
