package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.AppProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    private final AppProperties properties;

    public HealthCheckController(AppProperties properties) {
        this.properties = properties;
    }

    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Server is up");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok(String.format("%s - %s", properties.getName(), properties.getVersion()));
    }
}
