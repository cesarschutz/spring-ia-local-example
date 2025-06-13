package com.example.springialocal.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springialocal.domain.api.CardAccountApi;

@Service
public class CardAccountApiImpl implements CardAccountApi {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:3000/cards/";

    public CardAccountApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCardByUuid(String uuid) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(uuid)
                .build()
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Erro ao consultar api card-account");
        }

        return response.getBody();
    }
}