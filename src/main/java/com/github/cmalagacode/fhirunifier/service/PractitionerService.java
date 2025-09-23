package com.github.cmalagacode.fhirunifier.service;

import com.github.cmalagacode.fhirunifier.api.config.CignaConfig;
import com.github.cmalagacode.fhirunifier.api.config.Config;
import com.github.cmalagacode.fhirunifier.api.config.KaiserPermanente;
import com.github.cmalagacode.fhirunifier.api.config.UnitedHealthCareConfig;
import com.github.cmalagacode.fhirunifier.api.model.fhirlink.Link;
import com.github.cmalagacode.fhirunifier.api.model.fhirlocation.LocationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirorganization.OrganizationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner.PractitionerModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitionerrole.*;
import com.github.cmalagacode.fhirunifier.api.model.fhirresource.Resource;
import com.github.cmalagacode.fhirunifier.api.model.npiregistry.NPIRegistryConciseModel;
import com.github.cmalagacode.fhirunifier.api.model.npiregistry.NPIRegistryResponse;
import com.github.cmalagacode.fhirunifier.api.model.type.HealthPlanOrganizationName;
import com.github.cmalagacode.fhirunifier.api.model.unified.UnifiedConciseModel;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PractitionerService {

    private final NPIRegistryClient npiRegistryService;
    private final SimpleFetchClient simpleFetchClient;
    private final CignaConfig cignaConfig;
    private final UnitedHealthCareConfig unitedHealthCareConfig;
    private final KaiserPermanente kaiserPermanente;

    public PractitionerService(
            NPIRegistryClient npiRegistryService,
            SimpleFetchClient simpleFetchClient, CignaConfig cignaConfig, UnitedHealthCareConfig unitedHealthCareConfig,
            KaiserPermanente kaiserPermanente
    ) {
        this.npiRegistryService = npiRegistryService;
        this.simpleFetchClient = simpleFetchClient;
        this.cignaConfig = cignaConfig;
        this.unitedHealthCareConfig = unitedHealthCareConfig;
        this.kaiserPermanente = kaiserPermanente;
    }

    private boolean fhirMorePages(List<Link> links) {
        for (Link link : links) {
            if (link.getRelation().equalsIgnoreCase("next")) {
                return true;
            }
        }
        return false;
    }

    private Mono<PractitionerRoleModel> getHealthPlan(String lookupID, Config config) {
        String url = String.format(
                "%s?%s=%s",
                (config.getBaseURL() + "/" + config.getPractitionerRolePath()),
                config.getPractitionerRoleURLParameter(),
                lookupID
        );
        return simpleFetchClient.fetchPractitionerRole(url);
    }

    private Mono<List<OrganizationModel>> getOrganizations(
            Mono<PractitionerRoleModel> response, String baseURL
    ) {
        return response.flatMap(resp -> {
                    if (resp.getEntry() == null || resp.getEntry().isEmpty()) {
                        return Mono.just(new ArrayList<OrganizationModel>());
                    }
                    List<Resource> practitionerRoleResponseList = resp.getEntry().stream().map(entry -> entry.getResource()).toList();
                    // Fetch organizations
                    Mono<List<OrganizationModel>> organizationsMono = Flux.fromIterable(practitionerRoleResponseList)
                            .map(Resource::getOrganization)
                            .map(org -> org.getReference())
                            .flatMap(path -> {
                                String query = String.format("%s/%s", baseURL, path);
                                return simpleFetchClient.fetchOrganization(query);
                            })
                            .collectList();
                    return organizationsMono;
                })
                .onErrorMap(err -> new ServiceUnavailableException(err.getMessage()));
    }

    private Mono<List<PractitionerModel>> getPractitioners(
            Mono<PractitionerRoleModel> response, String baseURL
    ) {
        return response.flatMap(resp -> {
                    if (resp.getEntry() == null || resp.getEntry().isEmpty()) {
                        return Mono.just(new ArrayList<PractitionerModel>());
                    }
                    List<Resource> practitionerRoleResponseList = resp.getEntry().stream().map(entry -> entry.getResource()).toList();
                    // Fetch practitioners
                    Mono<List<PractitionerModel>> practitionersMono = Flux.fromIterable(practitionerRoleResponseList)
                            .map(Resource::getPractitioner)
                            .map(prac -> prac.getReference())
                            .flatMap(path -> {
                                String query = String.format("%s/%s", baseURL, path);
                                return simpleFetchClient.fetchPractitioner(query);
                            })
                            .collectList();
                    return practitionersMono;
                })
                .onErrorMap(err -> new ServiceUnavailableException(err.getMessage()));
    }

    private Mono<List<LocationModel>> getLocations(
            Mono<PractitionerRoleModel> response, String baseURL
    ) {
        return response.flatMap(resp -> {
                    if (resp.getEntry() == null || resp.getEntry().isEmpty()) {
                        return Mono.just(new ArrayList<LocationModel>());
                    }
                    List<Resource> practitionerRoleResponseList = resp.getEntry().stream().map(entry -> entry.getResource()).toList();
                    // Fetch locations
                    return Flux.fromIterable(practitionerRoleResponseList)
                            .flatMap(resource -> Flux.fromIterable(resource.getLocation()))
                            .flatMap(locationRef -> {
                                String path = locationRef.getReference(); // e.g., "Location/123"
                                String query = String.format("%s/%s", baseURL, path);
                                return simpleFetchClient.fetchLocation(query);
                            })
                            .collectList();
                })
                .onErrorMap(err -> new ServiceUnavailableException(err.getMessage()));
    }

    public Mono<ResponseEntity<UnifiedConciseModel>> getUnifiedConciseModel(
            String npi, HealthPlanOrganizationName target,
            String primaryTaxonomy
    ) {
        // PHASE 1: NPI REGISTRY
        Mono<NPIRegistryResponse> npiRegistry = npiRegistryService.fetchPractitionerByNPI(npi);
        // PHASE 2: FHIR ENDPOINT(S)
        Mono<PractitionerRoleModel> practitionerRole;
        Mono<List<PractitionerModel>> practitioners = null;
        Mono<List<OrganizationModel>> organizations = null;
        Mono<List<LocationModel>> locations = null;

        if (target == HealthPlanOrganizationName.CIGNA) {
            practitionerRole = getHealthPlan(npi, cignaConfig);
            // PHASE 3: Get Carrier Data

            practitioners = getPractitioners(
                    practitionerRole,
                    cignaConfig.getBaseURL()
            );
            organizations = getOrganizations(
                    practitionerRole,
                    cignaConfig.getBaseURL()
            );
            locations = getLocations(
                    practitionerRole,
                    cignaConfig.getBaseURL()
            );

        } else if (target == HealthPlanOrganizationName.UNITED_HEALTH_CARE) {
            practitionerRole = getHealthPlan(npi, unitedHealthCareConfig);
            // PHASE 3: Get Carrier Data

            practitioners = getPractitioners(
                    practitionerRole,
                    unitedHealthCareConfig.getBaseURL()
            );
            organizations = getOrganizations(
                    practitionerRole,
                    unitedHealthCareConfig.getBaseURL()
            );
            locations = getLocations(
                    practitionerRole,
                    unitedHealthCareConfig.getBaseURL()
            );
        } else if (target == HealthPlanOrganizationName.KAISER_PERMANENTE) {
            practitionerRole = getHealthPlan(npi, kaiserPermanente);
            // PHASE 3: Get Carrier Data

            practitioners = getPractitioners(
                    practitionerRole,
                    kaiserPermanente.getBaseURL()
            );
            organizations = getOrganizations(
                    practitionerRole,
                    kaiserPermanente.getBaseURL()
            );
            locations = getLocations(
                    practitionerRole,
                    kaiserPermanente.getBaseURL()
            );
        }

        // PHASE 4: Combine Model
        return Mono.zip(npiRegistry, practitioners, organizations, locations)
                .map(tupleData -> {
                    NPIRegistryResponse npiRegistryResponse = tupleData.getT1();
                    List<PractitionerModel> practitioner = tupleData.getT2();
                    List<OrganizationModel> organization = tupleData.getT3();
                    List<LocationModel> location = tupleData.getT4();

                    NPIRegistryConciseModel npiReg = new NPIRegistryConciseModel(
                            npiRegistryResponse.getResults().getFirst().getEnumerationType(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getCredential(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getSex(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getStatus(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getFirstName(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getLastName(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getMiddleName(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getSoleProprietor(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getEnumerationDate(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getLastUpdated(),
                            npiRegistryResponse.getResults().getFirst().getBasic().getCertificationDate(),
                            npiRegistryResponse.getResults().getFirst().getTaxonomies(),
                            npiRegistryResponse.getResults().getFirst().getAddresses()
                    );

                    UnifiedConciseModel model = new UnifiedConciseModel(
                            npi,
                            npiReg,
                            practitioner,
                            organization,
                            location
                    );
                    return ResponseEntity.ok(model);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorMap(err -> new ServiceUnavailableException(err.getMessage()));
    }
}
