package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public interface AbstractEnum {
    @JsonValue
    String getValue();

    @JsonCreator
    static <T extends Enum<T> & AbstractEnum> T fromValue(Class<T> enumType, String value) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(enumConstant -> enumConstant.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value for enum " + enumType.getSimpleName() + ": " + value));
    }
}