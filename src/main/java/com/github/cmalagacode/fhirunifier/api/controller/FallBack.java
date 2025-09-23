package com.github.cmalagacode.fhirunifier.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallBack {

    @RequestMapping("/**")
    public Mono<ResponseEntity<Map<String, String>>> handleUnknownPath() {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Path not found");
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }
}
