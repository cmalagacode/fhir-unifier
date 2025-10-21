package com.github.cmalagacode.fhirunifier;

import com.github.cmalagacode.fhirunifier.api.model.type.HealthPlanOrganizationName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class TestControllers {
    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    private final List<String> npiList = Arrays.asList("1467652149", "1477502557");

    @BeforeEach
    void setup() {
        this.webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .responseTimeout(java.time.Duration.ofSeconds(300))
                .build();
    }

    @Test
    public void getCigna() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.getFirst(), HealthPlanOrganizationName.CIGNA);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getUnitedHealthCare() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.get(1), HealthPlanOrganizationName.UNITED_HEALTH_CARE);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getKaiserPermanente() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.getFirst(), HealthPlanOrganizationName.KAISER_PERMANENTE);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getMolinaHealthCare() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.get(1), HealthPlanOrganizationName.MOLINA_HEALTHCARE);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getHealthCareServiceCorporation() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.get(1), HealthPlanOrganizationName.HEALTH_CARE_SERVICE_CORPORATION);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getIndependenceBlueCross() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.getFirst(), HealthPlanOrganizationName.INDEPENDENCE_BLUE_CROSS);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getElevanceHealth() {
        var test = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npiList.get(1), HealthPlanOrganizationName.ELEVANCE_HEALTH);

        webTestClient.get().uri(test)
                .exchange()
                .expectStatus().isOk();
    }
}
