package com.github.cmalagacode.fhirunifier.api.model.fhirspecialty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specialty {
    private List<SpecialtyCoding> coding;

    public Specialty() {}

    public Specialty(List<SpecialtyCoding> coding) {
        this.coding = coding;
    }

    public List<SpecialtyCoding> getCoding() {
        return coding;
    }
}
