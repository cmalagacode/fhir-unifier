package com.github.cmalagacode.fhirunifier.api.model.fhirentry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cmalagacode.fhirunifier.api.model.fhirresource.Resource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {
    private Resource resource;

    public Entry() {}

    public Entry(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
