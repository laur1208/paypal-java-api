package com.rlaur.paypal;

import com.rlaur.paypal.http.JsonResources;
import com.rlaur.paypal.http.RequestHeaders;

import javax.json.JsonObject;
import java.net.URI;

final class PPCatalogProduct extends Json implements CatalogProduct{

    private final JsonResources client;

    private final URI uri;

    private final PayPal payPal;

    private JsonObject catalogProduct;

    PPCatalogProduct(final JsonObject catalogProduct, final URI uri, final PayPal payPal) {
        this(catalogProduct, new JsonResources.FromHttp(new RequestHeaders()), uri, payPal);
    }

    PPCatalogProduct(final JsonObject catalogProduct, final JsonResources client, final URI uri, final PayPal payPal) {
        super(catalogProduct);
        this.client = client;
        this.uri = uri;
        this.payPal = payPal;
    }

    @Override
    public String id() {
        return this.getString("id");
    }

    @Override
    public String name() {
        return this.getString("name");
    }

    @Override
    public String description() {
        return this.getString("description");
    }

    @Override
    public String type() {
        return this.getString("type");
    }

    @Override
    public String category() {
        return catalogProduct().getString("category");
    }

    @Override
    public String imageUrl() {
        return catalogProduct().getString("image_url");
    }

    @Override
    public String homeUrl() {
        return catalogProduct().getString("home_url");
    }

    private JsonObject catalogProduct(){
        if(this.catalogProduct == null){
            this.catalogProduct = this.client.get(URI.create(this.uri + "/" + this.getString("id"))).json();
        }
        return this.catalogProduct;
    }
}
