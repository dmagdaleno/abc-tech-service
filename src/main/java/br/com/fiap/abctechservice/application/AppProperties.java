package br.com.fiap.abctechservice.application;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class AppProperties {
    private static final String PROPERTIES_FILE = "application.yml";

    private final Properties properties = new Properties();

    public AppProperties() {
        final var is = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return properties.getProperty("name");
    }

    public String getVersion() {
        return properties.getProperty("version");
    }
}
