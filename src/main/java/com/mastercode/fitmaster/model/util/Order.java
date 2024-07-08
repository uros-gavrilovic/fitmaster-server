package com.mastercode.fitmaster.model.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = Order.class)
@JsonDeserialize(as = Order.class)
public record Order(Direction direction, String property) {
    public Order {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        if (property == null || property.isBlank()) {
            throw new IllegalArgumentException("Property cannot be null or blank");
        }
    }

    public static Order of(String property) {
        return new Order(Direction.ASC, property);
    }

    public static Order of(String property, Direction direction) {
        return new Order(direction, property);
    }
}
