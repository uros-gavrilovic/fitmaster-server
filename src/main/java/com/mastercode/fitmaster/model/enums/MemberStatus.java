package com.mastercode.fitmaster.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum MemberStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), BANNED("BANNED"), PENDING("PENDING");

    private final String value;

    MemberStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MemberStatus fromValue(String value) {
        return Arrays.stream(MemberStatus.values())
                .filter(status -> status.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid MemberStatus value: " + value));
    }
}

