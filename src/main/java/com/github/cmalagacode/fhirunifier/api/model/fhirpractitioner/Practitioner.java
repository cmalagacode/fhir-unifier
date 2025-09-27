package com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Practitioner {
    private String reference;
    private String type;

    public Practitioner() {}

    public Practitioner(String reference, String type) {
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
