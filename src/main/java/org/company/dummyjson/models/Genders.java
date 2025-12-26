package org.company.dummyjson.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genders {

    MALE("male"),
    FEMALE("female");

    private final String value;

    Genders(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

