package com.github.cmalagacode.fhirunifier.api.model.fhirentry;

import com.github.cmalagacode.fhirunifier.api.model.fhirresource.Resource;

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
