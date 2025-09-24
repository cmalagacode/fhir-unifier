package com.github.cmalagacode.fhirunifier.api.model.fhirpractitionerrole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cmalagacode.fhirunifier.api.model.fhirentry.Entry;
import com.github.cmalagacode.fhirunifier.api.model.fhirlink.Link;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PractitionerRoleModel {
    private Optional<String> resourceType;
    private List<Link> link;
    private List<Entry> entry;

    public PractitionerRoleModel() {}

    public PractitionerRoleModel(Optional<String> resourceType, List<Link> link, List<Entry> entry) {
        this.resourceType = resourceType;
        this.link = link;
        this.entry = entry;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public List<Link> getLink() {
        return link;
    }

    public Optional<String> getResourceType() {
        return resourceType;
    }
}
