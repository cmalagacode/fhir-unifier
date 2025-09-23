package com.github.cmalagacode.fhirunifier.api.model.unified;

import com.github.cmalagacode.fhirunifier.api.model.fhirlocation.LocationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirorganization.OrganizationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner.PractitionerModel;
import com.github.cmalagacode.fhirunifier.api.model.npiregistry.NPIRegistryConciseModel;

import java.util.List;

public class UnifiedConciseModel {
    private final String npi;
    // NPI Registry
    private final NPIRegistryConciseModel npiRegistryConciseModel;
    private final List<PractitionerModel> practitionerModel;
    private final List<OrganizationModel> organizationModel;
    private final List<LocationModel> locationModel;


    public UnifiedConciseModel(
            String npi, NPIRegistryConciseModel npiRegistryConciseModel,
            List<PractitionerModel> practitionerModel,
            List<OrganizationModel> organizationModel,
            List<LocationModel> locationModel
    ) {
        this.npi = npi;
        this.npiRegistryConciseModel = npiRegistryConciseModel;
        this.practitionerModel = practitionerModel;
        this.organizationModel = organizationModel;
        this.locationModel = locationModel;

    }

    public String getNpi() { return npi; }
    public NPIRegistryConciseModel getNpiRegistryConciseModel() { return npiRegistryConciseModel; }
    public List<PractitionerModel> getPractitionerModel() { return practitionerModel; }
    public List<OrganizationModel> getOrganizationModel() { return organizationModel; }
    public List<LocationModel> getLocationModel() { return locationModel; }

}
