package com.github.cmalagacode.fhirunifier.api.model.fhirperiod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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
