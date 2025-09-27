package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPIRegistryResponse {
    private List<NPIRegistryResult> results;

    public NPIRegistryResponse() {}


    public NPIRegistryResponse(List<NPIRegistryResult> results) {
        this.results = results;
    }

    public List<NPIRegistryResult> getResults() {
        return results;
    }

}
