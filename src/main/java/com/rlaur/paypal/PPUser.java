package com.rlaur.paypal;

import java.net.URI;

public class PPUser implements User{

    private final URI uri;

    private final PayPal payPal;

    public PPUser(URI uri, PayPal payPal) {
        this.uri = uri;
        this.payPal = payPal;
    }

    @Override
    public CatalogProducts catalogProducts() {
        return new PPCatalogProducts(URI.create(uri + "/catalogs"), payPal);
    }
}
