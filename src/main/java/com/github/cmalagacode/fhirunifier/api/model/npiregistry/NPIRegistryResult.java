package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NPIRegistryResult {
    private String number;
    @JsonProperty("enumeration_type")
    private String enumerationType;
    private List<NPIRegistryAddresses> addresses;
    private NPIRegistryBasic basic;
    private List<NPIRegistryTaxonomies> taxonomies;

    public  NPIRegistryResult() {}

    public NPIRegistryResult(String number, List<NPIRegistryTaxonomies> taxonomies, NPIRegistryBasic basic, List<NPIRegistryAddresses> addresses, String enumerationType) {
        this.number = number;
        this.taxonomies = taxonomies;
        this.basic = basic;
        this.addresses = addresses;
        this.enumerationType = enumerationType;
    }

    public NPIRegistryBasic getBasic() {
        return basic;
    }

    public List<NPIRegistryAddresses> getAddresses() {
        return addresses;
    }

    public String getNumber() {
        return number;
    }

    public String getEnumerationType() {
        return enumerationType;
    }

    public List<NPIRegistryTaxonomies> getTaxonomies() {
        return taxonomies;
    }
//    private Basic basic;
}
