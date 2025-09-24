package com.github.cmalagacode.fhirunifier.api.model.fhiraddress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private List<String> line;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address() {}

    public Address(
            List<String> line, String city, String state, String postalCode,
            String country
    ) {
        this.line = line;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public List<String> getLine() {
        return line;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
