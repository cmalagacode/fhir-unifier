package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPIRegistryTaxonomies {
    private String code;
    @JsonProperty("taxonomy_group")
    private String taxonomyGroup;
    private String desc;
    private String state;
    private String license;
    private boolean primary;

    public NPIRegistryTaxonomies() {}

    public NPIRegistryTaxonomies(String code, String taxonomyGroup, String desc, String state, String license, boolean primary) {
        this.code = code;
        this.taxonomyGroup = taxonomyGroup;
        this.desc = desc;
        this.state = state;
        this.license = license;
        this.primary = primary;
    }

    public boolean isPrimary() {
        return primary;
    }

    public String getLicense() {
        return license;
    }

    public String getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }

    public String getTaxonomyGroup() {
        return taxonomyGroup;
    }

    public String getCode() {
        return code;
    }
}
