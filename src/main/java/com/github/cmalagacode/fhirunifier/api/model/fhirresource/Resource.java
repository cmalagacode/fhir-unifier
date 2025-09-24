package com.github.cmalagacode.fhirunifier.api.model.fhirresource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cmalagacode.fhirunifier.api.model.fhiridentifier.Identifier;
import com.github.cmalagacode.fhirunifier.api.model.fhirlocation.Location;
import com.github.cmalagacode.fhirunifier.api.model.fhirorganization.Organization;
import com.github.cmalagacode.fhirunifier.api.model.fhirperiod.Period;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner.Practitioner;
import com.github.cmalagacode.fhirunifier.api.model.fhirspecialty.Specialty;
import com.github.cmalagacode.fhirunifier.api.model.fhirtelecom.Telecom;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {
    private String resourceType;
    private String id;
    private List<Identifier> identifier;
    private String language;
    private boolean active;
    private Period period;
    private Practitioner practitioner;
    private Organization organization;
    private List<Location> location;
    private List<Telecom> telecom;
    private List<Specialty> specialty;

    public Resource() {}

    public Resource(
            String resourceType, List<Identifier> identifier, List<Telecom> telecom, List<Location> location, Organization organization,
            Practitioner practitioner, Period period, boolean active, String language, String id, List<Specialty> specialty
    ) {
        this.resourceType = resourceType;
        this.identifier = identifier;
        this.telecom = telecom;
        this.location = location;
        this.organization = organization;
        this.practitioner = practitioner;
        this.period = period;
        this.active = active;
        this.language = language;
        this.id = id;
        this.specialty = specialty;

    }

    public List<Specialty> getSpecialty() {
        return specialty;
    }

    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public boolean isActive() {
        return active;
    }

    public String getResourceType() {
        return resourceType;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public List<Location> getLocation() {
        return location;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Period getPeriod() {
        return period;
    }

    public boolean getActive() {
        return active;
    }

    public String getLanguage() {
        return language;
    }

    public String getId() {
        return id;
    }
}
