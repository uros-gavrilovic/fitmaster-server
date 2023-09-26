package com.mastercode.fitmaster.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlanStatus implements AbstractEnum {
    COMPLETED("COMPLETED"), AWAITING("AWAITING"), EXPIRED("EXPIRED"), CANCELLED("CANCELLED");

    private final String value;
}
