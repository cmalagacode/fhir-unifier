package com.github.cmalagacode.fhirunifier.api.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    private static final int CONNECT_TIMEOUT_MS = 10_000;
    private static final int RESPONSE_TIMEOUT_SECONDS = 120;
    private static final int READ_TIMEOUT_SECONDS = 120;
    private static final int WRITE_TIMEOUT_SECONDS = 10;
    private static final int MAX_IN_MEMORY_SIZE = 10 * 1024 * 1024;


    @Bean
    public ReactiveOAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService(
            ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ReactiveOAuth2AuthorizedClientService authorizedClientService) {

        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientService);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    @Bean
    public WebClient oauth2WebClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MS)
                .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)));

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2FilterFunction =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2FilterFunction.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter(oauth2FilterFunction)
                .build();
    }

    @Bean
    public WebClient simpleWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MS)
                .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))
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
}
