package com.github.cmalagacode.fhirunifier.service;

import com.github.cmalagacode.fhirunifier.api.model.fhirlocation.LocationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirorganization.OrganizationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner.PractitionerModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitionerrole.PractitionerRoleModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class SimpleFetchClient {

    private final WebClient client;

    public SimpleFetchClient(@Qualifier("simpleWebClient") WebClient simpleWebClient) {
        this.client = simpleWebClient;
    }

    public Mono<PractitionerRoleModel> fetchPractitionerRole(String url) {
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PractitionerRoleModel.class)
                .onErrorResume(e -> Mono.just(new PractitionerRoleModel()));
    }

    public Mono<PractitionerModel> fetchPractitioner(String url) {
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PractitionerModel.class)
                .onErrorResume(e -> Mono.just(new PractitionerModel()));
    }

    public Mono<OrganizationModel> fetchOrganization(String url) {
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OrganizationModel.class)
                .onErrorResume(e -> Mono.just(new OrganizationModel()));
    }

    public Mono<LocationModel> fetchLocation(String url) {
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LocationModel.class)
                .onErrorResume(e -> Mono.just(new LocationModel()));
    }
}