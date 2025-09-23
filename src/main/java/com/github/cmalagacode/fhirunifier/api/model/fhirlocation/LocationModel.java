package com.github.cmalagacode.fhirunifier.api.model.fhirlocation;

import com.github.cmalagacode.fhirunifier.api.model.fhiraddress.Address;
import com.github.cmalagacode.fhirunifier.api.model.fhirtelecom.Telecom;

import java.util.List;

public class LocationModel {
    private String id;
    private String language;
    private String status;
    private String name;
    private Address address;
    private List<Telecom> telecom;

    public LocationModel() {}

    public LocationModel(String id, List<Telecom> telecom, Address address, String name, String status, String language) {
        this.id = id;
        this.telecom = telecom;
        this.address = address;
        this.name = name;
        this.status = status;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }
}
