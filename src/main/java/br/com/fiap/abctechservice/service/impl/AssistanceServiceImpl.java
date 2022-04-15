package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.service.AssistanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceServiceImpl implements AssistanceService {
    private final AssistanceRepository repository;

    public AssistanceServiceImpl(AssistanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Assistance> getAssists() {
        return repository.findAll();
    }
}
