package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NPIRegistryBasic {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    private String credential;
    @JsonProperty("sole_proprietor")
    private String soleProprietor;
    private String sex;

    public String getSex() {
        return sex;
    }

    public String getSoleProprietor() {
        return soleProprietor;
    }

    public String getEnumerationDate() {
        return enumerationDate;
    }

    public String getCertificationDate() {
        return certificationDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("enumeration_date")
    private String enumerationDate;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("certification_date")
    private String certificationDate;
    private String status;

    public NPIRegistryBasic() {}

    public NPIRegistryBasic(String firstName, String credentials, String middleName, String lastName) {
        this.firstName = firstName;
        this.credential = credentials;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCredential() {
        return credential;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }
}
