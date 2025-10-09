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
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.time.Duration;
import reactor.util.retry.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Oauth2FetchClient {
    private static final Logger log = LoggerFactory.getLogger(Oauth2FetchClient.class);

    private final WebClient client;

    public Oauth2FetchClient(@Qualifier("oauth2WebClient") WebClient oauth2WebClient) {
        this.client = oauth2WebClient;
    }

    private <T> Mono<T> fetchWithRetry(String url, String registrationId, Class<T> responseType, T emptyResponse) {
        log.debug("Fetching {} from URL: {} with registrationId: {}",
                responseType.getSimpleName(), url, registrationId);

        return client.get()
                .uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
                        .clientRegistrationId(registrationId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .retryWhen(Retry.backoff(8, Duration.ofSeconds(3))
                        .maxBackoff(Duration.ofSeconds(30))
                        .jitter(0.75)
                        .filter(throwable -> {
                            if (throwable instanceof WebClientResponseException) {
                                WebClientResponseException ex = (WebClientResponseException) throwable;
                                // retry on 429 and 503
                                return ex.getStatusCode().value() == 429 || ex.getStatusCode().value() == 503;
                            }
                            return false;
                        })
                        .doBeforeRetry(retrySignal -> {
                            if (retrySignal.failure() instanceof WebClientResponseException) {
                                WebClientResponseException ex = (WebClientResponseException) retrySignal.failure();
                                String retryAfter = ex.getHeaders().getFirst("Retry-After");
                                if (retryAfter != null) {
                                    log.warn("Rate limited ({}). Retry-After: {} seconds. Attempt: {}/8",
                                            ex.getStatusCode().value(),
                                            retryAfter,
                                            retrySignal.totalRetries() + 1);
                                } else {
                                    log.warn("Rate limited or service unavailable ({}). Retrying with backoff. Attempt: {}/8",
                                            ex.getStatusCode().value(),
                                            retrySignal.totalRetries() + 1);
                                }
                            }
                        })
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            log.error("Max retries exceeded for URL: {}. Original error: {}",
                                    url, retrySignal.failure().getMessage());
                            return retrySignal.failure();
                        }))
                .doOnSuccess(response -> {
                    if (responseType == PractitionerRoleModel.class) {
                        PractitionerRoleModel model = (PractitionerRoleModel) response;
                        log.debug("Successfully fetched {}. Entry count: {}",
                                responseType.getSimpleName(), model.getEntry() != null ? model.getEntry().size() : 0);
                    } else {
                        log.debug("Successfully fetched {} from URL: {}", responseType.getSimpleName(), url);
                    }
                })
                .onErrorResume(e -> {
                    log.error("Error fetching {} from URL: {}. Error: {}",
                            responseType.getSimpleName(), url, e.getMessage());
                    return Mono.just(emptyResponse);
                });
    }

    public Mono<PractitionerRoleModel> fetchPractitionerRole(String url, String registrationId) {
        return fetchWithRetry(url, registrationId, PractitionerRoleModel.class, new PractitionerRoleModel());
    }

    public Mono<PractitionerModel> fetchPractitioner(String url, String registrationId) {
        return fetchWithRetry(url, registrationId, PractitionerModel.class, new PractitionerModel());
    }

    public Mono<OrganizationModel> fetchOrganization(String url, String registrationId) {
        return fetchWithRetry(url, registrationId, OrganizationModel.class, new OrganizationModel());
    }

    public Mono<LocationModel> fetchLocation(String url, String registrationId) {
        return fetchWithRetry(url, registrationId, LocationModel.class, new LocationModel());
    }
}
