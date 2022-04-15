package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.service.impl.AssistanceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssistanceServiceTest {
    @Mock
    private AssistanceRepository repository;
    private AssistanceService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new AssistanceServiceImpl(repository);
    }

    @Test
    public void shouldGetList() {
        final var assistance1 = new Assistance(1L, "assistencia numero um", "assistencia 1");
        final var assistance2 = new Assistance(2L, "assistencia numero dois", "assistencia 2");

        when(repository.findAll()).thenReturn(List.of(assistance1, assistance2));

        final var assists = service.getAssists();

        assertEquals(2, assists.size());
        assertSame(assistance1, assists.get(0));
        assertSame(assistance2, assists.get(1));
    }

    @Test
    public void shouldGetEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        final var assists = service.getAssists();

        assertEquals(0, assists.size());
    }
}