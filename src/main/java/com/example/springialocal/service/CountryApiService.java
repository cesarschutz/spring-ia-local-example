package com.example.springialocal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CountryApiService {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://restcountries.com/v3.1/name/";

    public CountryApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCountry(String country) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(country)
                .build()
                .toUriString();

        ResponseEntity<String> response = restTemplate. getForEntity(url, String.class);
        
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Erro ao consultar api restcountries");
        }

        return response.getBody();
    }
}