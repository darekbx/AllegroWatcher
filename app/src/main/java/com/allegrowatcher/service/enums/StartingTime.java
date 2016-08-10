package com.allegrowatcher.service.enums;

/**
 * Created by daba on 2016-08-10.
 */

public enum StartingTime {
    HOURS_1("1h"),
    HOURS_2("2h"),
    HOURS_3("3h"),
    HOURS_4("4h"),
    HOURS_5("5h"),
    HOURS_12("12h"),
    HOURS_24("24h"),
    DAYS_2("2d"),
    DAYS_3("3d"),
    DAYS_4("4d"),
    DAYS_5("5d"),
    DAYS_6("6d"),
    DAYS_7("7d");

    private String value;

    StartingTime(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}