package com.github.cmalagacode.fhirunifier.api.model.fhirlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    private String relation;
    private String url;

    public Link() {}


    public Link(String relation, String url) {
        this.relation = relation;
        this.url = url;
    }

    public String getRelation() {
        return relation;
    }

    public String getUrl() {
        return url;
    }
}
