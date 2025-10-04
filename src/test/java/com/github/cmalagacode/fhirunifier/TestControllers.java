package com.github.cmalagacode.fhirunifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.github.cmalagacode.fhirunifier.api.model.type.HealthPlanOrganizationName;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllers {
    @Autowired
    private MockMvc mockMvc;
    private List<String> npiList = Arrays.asList("1467652149", "1477502557");

    @Test
    public void getCigna() throws Exception {
        for (int i = 0; i < npiList.size(); i++) {
            var query = String.format(
                    "/unifier/v1?npi=%s&model=CONCISE&target=%s",
                    npiList.get(i),
                    HealthPlanOrganizationName.CIGNA
            );
            mockMvc.perform(get(query))
                    .andExpect(status().isOk());
        }
    }
    @Test
    public void getUnitedHealthCare() throws Exception {
        for (int i = 0; i < npiList.size(); i++) {
            var query = String.format(
                    "/unifier/v1?npi=%s&model=CONCISE&target=%s",
                    npiList.get(i),
                    HealthPlanOrganizationName.UNITED_HEALTH_CARE
            );
            mockMvc.perform(get(query))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void getIndependenceBlueCross() throws Exception {
        for (int i = 0; i < npiList.size(); i++) {
            var query = String.format(
                    "/unifier/v1?npi=%s&model=CONCISE&target=%s",
                    npiList.get(i),
                    HealthPlanOrganizationName.INDEPENDENCE_BLUE_CROSS
            );
            mockMvc.perform(get(query))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void getKaiserPermanente() throws Exception {
        for (int i = 0; i < npiList.size(); i++) {
            var query = String.format(
                    "/unifier/v1?npi=%s&model=CONCISE&target=%s",
                    npiList.get(i),
                    HealthPlanOrganizationName.KAISER_PERMANENTE
            );
            mockMvc.perform(get(query))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void getMolinaHealthCare() throws Exception {
        for (int i = 0; i < npiList.size(); i++) {
            var query = String.format(
                    "/unifier/v1?npi=%s&model=CONCISE&target=%s",
                    npiList.get(i),
                    HealthPlanOrganizationName.MOLINA_HEALTHCARE
            );
            mockMvc.perform(get(query))
                    .andExpect(status().isOk());
        }
    }
}
