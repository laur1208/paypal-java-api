package com.rlaur.paypal;

import javax.json.JsonObject;

/**
 * Product of a merchant.
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 * @see <a href="https://developer.paypal.com/docs/api/catalog-products/v1/">Catalog Products</a>
 */
public interface CatalogProduct extends JsonObject {

    /**
     * The ID of the product. You can specify the SKU for the product. If you omit the ID, the system generates it. System-generated IDs have the PROD- prefix.
     * @return String id
     */
    String id();

    /**
     * The product name.
     * @return String name.
     */
    String name();

    /**
     * The product description.
     * @return String description.
     */
    String description();

    /**
     * The product type. Indicates whether the product is physical or digital goods, or a service.
     * @return String type
     */
    String type();

    /**
     * The category of a product.
     * @return String category
     */
    String category();

    /**
     * The image URL for the product.
     * @return String image URL.
     */
    String imageUrl();

    /**
     * The home page URL for the product.
     * @return String home page.
     */
    String homeUrl();

    /**
     * Possible types of products.
     */
    class Type {

        Type(){}

        public static final String PHYSICAL = "Physical goods";

        public static final String DIGITAL = "Digital goods";

        public static final String SERVICE = "A service. For example, technical support";

    }
}
