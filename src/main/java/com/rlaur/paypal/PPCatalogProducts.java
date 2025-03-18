package com.rlaur.paypal;

import com.rlaur.paypal.http.Headers;
import com.rlaur.paypal.http.JsonResources;
import com.rlaur.paypal.http.RequestHeaders;

import javax.json.JsonObject;
import java.net.URI;
import java.util.Iterator;

final class PPCatalogProducts implements CatalogProducts {

    private final JsonResources resources;

    private final URI uri;

    private final PayPal payPal;

    PPCatalogProducts(final URI uri, final PayPal payPal) {
        this(new JsonResources.FromHttp(new RequestHeaders()), uri, payPal);
    }

    PPCatalogProducts(final JsonResources resources, final URI uri, final PayPal payPal) {
        this.resources = resources;
        this.uri = uri;
        this.payPal = payPal;
    }


    @Override
    public CatalogProduct create(JsonObject catalogProduct) {
        final URI uri = URI.create(this.uri + "/products");
        JsonObject product = this.resources.post(uri, catalogProduct).json();
        return new PPCatalogProduct(
                product,
                this.resources,
                uri,
                this.payPal
        );
    }

    @Override
    public Iterator<CatalogProduct> iterator() {
        return null;
    }
}
