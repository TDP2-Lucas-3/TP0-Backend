package tdp2.lucas3.tp0.service;

import org.springframework.context.annotation.Bean;

public class RestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
