package com.github.cmalagacode.fhirunifier;

import com.github.cmalagacode.fhirunifier.api.model.type.HealthPlanOrganizationName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
public class TestControllers {

    @Autowired
    private WebTestClient webTestClient;

    private final List<String> npiList = Arrays.asList("1467652149", "1477502557");

    @Test
    public void getCigna() {
        for (String npi : npiList) {
            var query = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npi, HealthPlanOrganizationName.CIGNA);
            webTestClient.get().uri(query)
                    .exchange()
                    .expectStatus().isOk();
        }
    }

    @Test
    public void getUnitedHealthCare() {
        for (String npi : npiList) {
            var query = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npi, HealthPlanOrganizationName.UNITED_HEALTH_CARE);
            webTestClient.get().uri(query)
                    .exchange()
                    .expectStatus().isOk();
        }
    }

    @Test
    public void getKaiserPermanente() {
        for (String npi : npiList) {
            var query = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npi, HealthPlanOrganizationName.KAISER_PERMANENTE);
            webTestClient.get().uri(query)
                    .exchange()
                    .expectStatus().isOk();
        }
    }

    @Test
    public void getMolinaHealthCare() {
        for (String npi : npiList) {
            var query = String.format("/unifier/v1?npi=%s&model=CONCISE&target=%s", npi, HealthPlanOrganizationName.MOLINA_HEALTHCARE);
            webTestClient.get().uri(query)
                    .exchange()
                    .expectStatus().isOk();
        }
    }
}
