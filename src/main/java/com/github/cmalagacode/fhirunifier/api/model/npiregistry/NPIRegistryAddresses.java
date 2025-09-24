package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPIRegistryAddresses {
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("address_purpose")
    private String addressPurpose;
    @JsonProperty("address_type")
    private String addressType;
    @JsonProperty("address_1")
    private String address1;
    private String city;
    private String state;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("telephone_number")
    private String telephoneNumber;
    @JsonProperty("fax_number")
    private String faxNumber;

    public NPIRegistryAddresses() {}

    public NPIRegistryAddresses(
            String countryCode, String countryName,
            String addressType, String addressPurpose,
            String address1, String city, String state,
            String postalCode, String telephoneNumber,
            String faxNumber
    ) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.addressType = addressType;
        this.addressPurpose = addressPurpose;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddressType() {
        return addressType;
    }

    public String getAddressPurpose() {
        return addressPurpose;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
