package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.AssistanceApplication;
import br.com.fiap.abctechservice.application.dto.AssistDto;
import br.com.fiap.abctechservice.service.AssistanceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    private final AssistanceService service;

    public AssistanceApplicationImpl(AssistanceService service) {
        this.service = service;
    }

    @Override
    public List<AssistDto> getAssists() {
        return service.getAssists().stream()
            .map(assistance -> new AssistDto(assistance.getId(), assistance.getName(), assistance.getDescription()))
            .collect(Collectors.toList());
    }
}