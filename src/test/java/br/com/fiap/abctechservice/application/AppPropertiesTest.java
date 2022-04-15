package br.com.fiap.abctechservice.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AppPropertiesTest {
    private final AppProperties properties = new AppProperties();

    @Test
    void shouldGetApplicationName() {
        assertNotNull(properties.getName());
        assertEquals("abc-tech-service", properties.getName());
    }

    @Test
    void shouldGetApplicationVersion() {
        assertNotNull(properties.getVersion());
        assertEquals("0.0.3-SNAPSHOT", properties.getVersion());
    }
}