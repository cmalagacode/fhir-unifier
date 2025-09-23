package com.github.cmalagacode.fhirunifier.api.model.fhiridentifier;


public class Identifier {
    private String system;
    private String value;

    public Identifier() {
    }
    public Identifier(String system, String value) {
        this.system = system;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getSystem() {
        return system;
    }
}
