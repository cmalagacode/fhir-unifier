package com.github.cmalagacode.fhirunifier.api.model.fhirperiod;

public class Period {
    private String start;
    private String end;

    public  Period() {}

    public Period(String end, String start) {
        this.end = end;
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
