package com.github.cmalagacode.fhirunifier.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError(HttpServletRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid path or request");
        error.put("request", request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
