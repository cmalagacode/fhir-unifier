package com.github.cmalagacode.fhirunifier.api.model.fhirorganization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cmalagacode.fhirunifier.api.model.fhiraddress.Address;
import com.github.cmalagacode.fhirunifier.api.model.fhirtelecom.Telecom;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationModel {
    private String id;
    private String language;
    private String resourceType;
    private boolean active;
    private String name;
    private List<Telecom> telecom;
    private List<Address>  address;

    public OrganizationModel() {}


    public OrganizationModel(
            String id, String language, String resourceType,
            boolean active, String name,
            List<Telecom> telecom, List<Address> address
    ) {
        this.id = id;
        this.language = language;
        this.resourceType = resourceType;
        this.active = active;
        this.name = name;
        this.telecom = telecom;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getResourceType() {
        return resourceType;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }


    public List<Telecom> getTelecom() {
        return telecom;
    }

    public List<Address> getAddress() {
        return address;
    }
}
