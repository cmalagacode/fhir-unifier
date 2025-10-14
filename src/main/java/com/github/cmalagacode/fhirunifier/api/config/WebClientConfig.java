
package com.github.cmalagacode.fhirunifier.api.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    private static final int CONNECT_TIMEOUT_MS = 10_000;  // 10 seconds
    private static final int RESPONSE_TIMEOUT_SECONDS = 120;
    private static final int READ_TIMEOUT_SECONDS = 120;
    private static final int WRITE_TIMEOUT_SECONDS = 10;
    private static final int MAX_IN_MEMORY_SIZE = 10 * 1024 * 1024;  // 10 MB

    @Bean
    @Primary
    OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository
    ) {
        OAuth2AuthorizedClientProvider provider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager manager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository));
        manager.setAuthorizedClientProvider(provider);

        return manager;
    }

    @Bean
    public WebClient simpleWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MS)  // 10 seconds to establish connection
                .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))  // 30 seconds for response
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)));

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    @Bean
    WebClient oauth2WebClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MS)  // 10 seconds to establish connection
                .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))  // 30 seconds for response
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)));

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter((request, next) -> {
                    return next.exchange(request);
                })
                .build();
    }
}