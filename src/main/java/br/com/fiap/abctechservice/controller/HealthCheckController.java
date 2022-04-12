package br.com.fiap.abctechservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Server is up");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        final var properties = new Properties();
        final var is = getClass().getClassLoader().getResourceAsStream("application.yml");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(String.format("%s - %s", properties.getProperty("name"), properties.getProperty("version")));
    }
}
