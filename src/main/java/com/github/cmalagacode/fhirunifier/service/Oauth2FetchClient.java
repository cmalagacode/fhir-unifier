package com.github.cmalagacode.fhirunifier.service;

import com.github.cmalagacode.fhirunifier.api.model.fhirlocation.LocationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirorganization.OrganizationModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitioner.PractitionerModel;
import com.github.cmalagacode.fhirunifier.api.model.fhirpractitionerrole.PractitionerRoleModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Oauth2FetchClient {
    private final WebClient client;

    public Oauth2FetchClient(@Qualifier("oauth2WebClient") WebClient oauth2WebClient) {
        this.client = oauth2WebClient;
    }

    public Mono<PractitionerRoleModel> fetchPractitionerRole(String url, String registrationId) {
        return client.get()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(registrationId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PractitionerRoleModel.class)
                .onErrorResume(e -> Mono.just(new PractitionerRoleModel()));
    }

    public Mono<PractitionerModel> fetchPractitioner(String url, String registrationId) {
        return client.get()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(registrationId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PractitionerModel.class)
                .onErrorResume(e -> Mono.just(new PractitionerModel()));
    }

    public Mono<OrganizationModel> fetchOrganization(String url, String registrationId) {
        return client.get()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(registrationId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OrganizationModel.class)
                .onErrorResume(e -> Mono.just(new OrganizationModel()));
    }

    public Mono<LocationModel> fetchLocation(String url, String registrationId) {
        return client.get()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(registrationId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LocationModel.class)
                .onErrorResume(e -> Mono.just(new LocationModel()));
    }
}
