package com.rlaur.paypal;

import javax.json.*;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A Json object returned by PayPal's API.
 * Each model in PayPal's Java API is represented as a Json for the ease of extension.
 * @author Rujoiu Laurentiu (rujoiu.laurentiu94@gmail.com)
 * @since 0.0.1
 * @see <a href="https://amihaiemil.com/2019/03/31/polymorphic-input-output-data.html">Polymorphic Input/Output concept</a>
 */
abstract class Json implements JsonObject {

    /**
     * The JsonObject resource in question.
     */
    private final JsonObject resource;

    /**
     * Ctor.
     * @param resource Supply the JsonObject.
     */
    Json(final Supplier<JsonObject> resource) {
        this(resource.get());
    }

    /**
     * Ctor.
     * @param resource The JsonObject resource in question.
     */
    Json(final JsonObject resource) {
        this.resource = resource;
    }

    @Override
    public final JsonArray getJsonArray(final String name) {
        return this.resource.getJsonArray(name);
    }

    @Override
    public final JsonObject getJsonObject(final String name) {
        return this.resource.getJsonObject(name);
    }

    @Override
    public final JsonNumber getJsonNumber(final String name) {
        return this.resource.getJsonNumber(name);
    }

    @Override
    public final JsonString getJsonString(final String name) {
        return this.resource.getJsonString(name);
    }

    @Override
    public final String getString(final String name) {
        return this.resource.getString(name);
    }

    @Override
    public final String getString(final String name,
                                  final String defaultValue
    ) {
        return this.resource.getString(name, defaultValue);
    }

    @Override
    public final int getInt(final String name) {
        return this.resource.getInt(name);
    }

    @Override
    public final int getInt(final String name, final int defaultValue) {
        return this.resource.getInt(name, defaultValue);
    }

    @Override
    public final boolean getBoolean(final String name) {
        return this.resource.getBoolean(name);
    }

    @Override
    public final boolean getBoolean(final String name,
                                    final boolean defaultValue
    ) {
        return this.resource.getBoolean(name, defaultValue);
    }

    @Override
    public final boolean isNull(final String name) {
        return this.resource.isNull(name);
    }

    @Override
    public final ValueType getValueType() {
        return this.resource.getValueType();
    }

    @Override
    public final int size() {
        return this.resource.size();
    }

    @Override
    public final boolean isEmpty() {
        return this.resource.isEmpty();
    }

    @Override
    public final boolean containsKey(final Object key) {
        return this.resource.containsKey(key);
    }

    @Override
    public final boolean containsValue(final Object value) {
        return this.resource.containsValue(value);
    }

    @Override
    public final JsonValue get(final Object key) {
        return this.resource.get(key);
    }

    @Override
    public final JsonValue put(final String key, final JsonValue value) {
        return this.resource.put(key, value);
    }

    @Override
    public final JsonValue remove(final Object key) {
        return this.resource.remove(key);
    }

    @Override
    public final void putAll(
            final Map<? extends String, ? extends JsonValue> map
    ) {
        this.resource.putAll(map);
    }

    @Override
    public final void clear() {
        this.resource.clear();
    }

    @Override
    public final Set<String> keySet() {
        return this.resource.keySet();
    }

    @Override
    public final Collection<JsonValue> values() {
        return this.resource.values();
    }

    @Override
    public final Set<Entry<String, JsonValue>> entrySet() {
        return this.resource.entrySet();
    }

    @Override
    public final String toString() {
        return this.resource.toString();
    }

    @Override
    public final boolean equals(final Object other) {
        return this.resource.equals(other);
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.resource);
        return hash;
    }

}
