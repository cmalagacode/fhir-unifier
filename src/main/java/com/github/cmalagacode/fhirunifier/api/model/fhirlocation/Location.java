package com.github.cmalagacode.fhirunifier.api.model.fhirlocation;

public class Location {
    private String reference;
    private String type;

    public Location() {}

    public Location(String reference, String type) {
        this.reference = reference;
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public String getType() {
        return type;
    }
}
