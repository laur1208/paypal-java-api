package com.rlaur.paypal;

import com.rlaur.paypal.http.JsonResources;

import javax.json.JsonObject;
import java.net.URI;
import java.util.Iterator;

final class PPCatalogProducts implements CatalogProducts {

    private final JsonResources resources;

    private final URI uri;

    private final PayPal payPal;

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
    public CatalogProduct get(String id) {
        final URI uri = URI.create(this.uri + "/products/" + id);
        System.out.println("get " + id);
        JsonObject product = this.resources.get(uri).json();
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
