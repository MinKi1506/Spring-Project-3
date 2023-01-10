package com.example.minki.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfiguration {


    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8085")
//                .baseUrl("http://192.168.50.153:8085")
                .build();

        return webClient;
    }
}
