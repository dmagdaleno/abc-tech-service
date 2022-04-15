package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.service.AssistanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {
    private final AssistanceService service;

    public AssistanceController(AssistanceService service) {
        this.service = service;
    }

    public ResponseEntity<List<Assistance>> getAssists() {
        return ResponseEntity.ok(service.getAssists());
    }
}
