package com.megazone.ERPSystem_phase3_Common.financial.model.ledger.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum DailyAndMonthType {
    DAILY("Daily"),
    MONTHLY("Monthly");

    private final String name;

    DailyAndMonthType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static DailyAndMonthType of(String progress) {
        return Arrays.stream(DailyAndMonthType.values())
                .filter(i -> i.name.equals(progress))
                .findAny()
                .orElse(null);
    }
}