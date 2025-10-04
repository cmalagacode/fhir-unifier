package com.github.cmalagacode.fhirunifier.api.controller;

import com.github.cmalagacode.fhirunifier.api.model.type.HealthPlanOrganizationName;
import com.github.cmalagacode.fhirunifier.api.model.unified.UnifiedConciseModel;
import com.github.cmalagacode.fhirunifier.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.github.cmalagacode.fhirunifier.api.model.type.Model;


@RestController
@RequestMapping("/unifier")
public class UnifyController {
    private final PractitionerService practitionerService;

    @Autowired
    public UnifyController(
            PractitionerService practitionerService
    ) {
        this.practitionerService = practitionerService;
    }

    @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UnifiedConciseModel>> getModel(
            @RequestParam(name = "model", required = true) Model model,
            @RequestParam(name = "npi", required = true) String npi,
            @RequestParam(name = "target", required = true) HealthPlanOrganizationName target
    ) {
        if (model == Model.CONCISE) {
            return practitionerService.getUnifiedConciseModel(npi, target);
        }
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
