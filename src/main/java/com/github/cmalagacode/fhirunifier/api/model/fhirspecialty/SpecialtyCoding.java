package com.github.cmalagacode.fhirunifier.api.model.fhirspecialty;

public class SpecialtyCoding {
    private String system;
    private String code;
    private String display;

    public SpecialtyCoding() {}

    public SpecialtyCoding(String display, String system, String code) {
        this.display = display;
        this.system = system;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }

    public String getSystem() {
        return system;
    }
}
