package com.github.cmalagacode.fhirunifier.api.model.fhirtelecom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Telecom {
    private String system;
    private String value;
    private Optional<Integer> rank;

    public Telecom() {}

    public Telecom(Optional<Integer> rank, String value, String system) {
        this.rank = rank;
        this.value = value;
        this.system = system;
    }

    public String getValue() {
        return value;
    }

    public Optional<Integer> getRank() {
        return rank;
    }

    public String getSystem() {
        return system;
    }
}
