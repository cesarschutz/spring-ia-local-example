package com.example.springialocal.tools;

import com.example.springialocal.service.CountryApiService;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class CountryApiTools {
    private final CountryApiService countryApiService;

    public CountryApiTools(CountryApiService publicApiService) {
        this.countryApiService = publicApiService;
    }

    @Tool(
        name = "getCountry",
        description = "Sempre utilize essa ferramenta para consultar informações sobre um país"
    )
    public String getCountry(String country) {
        try {
            return countryApiService.getCountry(country);
        } catch (Exception e) {
            return "Desculpe, não foi possível consultar o país. Erro: " + e.getMessage();
        }
    }
}