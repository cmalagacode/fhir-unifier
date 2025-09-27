package com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cmalagacode.fhirunifier.api.model.fhiraddress.Address;
import com.github.cmalagacode.fhirunifier.api.model.fhirtelecom.Telecom;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PractitionerModel {
    private String id;
    private String language;
    private boolean active;
    private String gender;
    private String birthDate;
    private List<Telecom> telecom;
    private List<Address> address;

    public PractitionerModel() {}


    public PractitionerModel(
            String id, String language, boolean active, String gender,
            String birthDate, List<Telecom> telecom, List<Address> address
    ) {
        this.id = id;
        this.language = language;
        this.active = active;
        this.gender = gender;
        this.birthDate = birthDate;
        this.telecom = telecom;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public List<Address> getAddress() {
        return address;
    }
}
