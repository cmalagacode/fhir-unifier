package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class NPIRegistryResponse {
    private List<NPIRegistryResult> results;

    public NPIRegistryResponse() {}


    @JsonIgnoreProperties(ignoreUnknown = true)
    public NPIRegistryResponse(List<NPIRegistryResult> results) {
        this.results = results;
    }

    public List<NPIRegistryResult> getResults() {
        return results;
    }

}
