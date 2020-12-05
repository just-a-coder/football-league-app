package com.pkg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Data
public class ApiConfig {

    @Value("${config.api.baseUrl}")
    private String baseUrl;

    @Value("${config.api.key}")
    private String key;

    @Value("${config.api.action.leagues}")
    private String actionLeague;

    @Value("${config.api.action.countries}")
    private String actionCountries;

    @Value("${config.api.action.standings}")
    private String teamStandings;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
