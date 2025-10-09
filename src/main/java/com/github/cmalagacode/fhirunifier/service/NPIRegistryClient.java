package com.github.cmalagacode.fhirunifier.service;

import com.github.cmalagacode.fhirunifier.api.model.npiregistry.NPIRegistryResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Service
public class NPIRegistryClient {

    private final WebClient registryClient;

    public NPIRegistryClient(WebClient.Builder builder) {
        registryClient = builder.baseUrl("https://npiregistry.cms.hhs.gov/api").build();
    }

    public Mono<NPIRegistryResponse> fetchPractitionerByNPI(String npi) {
        return registryClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("number", npi)
                        .queryParam("version", "2.1")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(NPIRegistryResponse.class)
                .timeout(Duration.ofSeconds(30));
    }
}
