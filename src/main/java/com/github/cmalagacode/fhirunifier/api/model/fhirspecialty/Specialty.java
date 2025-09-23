package com.github.cmalagacode.fhirunifier.api.model.fhirspecialty;

import java.util.List;

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
