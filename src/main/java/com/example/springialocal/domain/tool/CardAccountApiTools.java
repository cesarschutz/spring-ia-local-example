package com.example.springialocal.domain.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.example.springialocal.domain.api.CardAccountApi;

@Component
public class CardAccountApiTools {
    private static final Logger logger = LoggerFactory.getLogger(CardAccountApiTools.class);

    private final CardAccountApi cardAccountApi;

    public CardAccountApiTools(CardAccountApi cardAccountApi) {
        this.cardAccountApi = cardAccountApi;
    }

    @Tool(
        name = "get_card_by_uuid",
        description = "Consulta dados de um cartão através do seu UUID."
    )
    public String getCountry(String country) {
        logger.info("---> tool:get_card_by_uuid invoked");
        try {
            return cardAccountApi.getCardByUuid(country);
        } catch (Exception e) {
            return "Desculpe, não foi possível consultar a api card-account. Erro: " + e.getMessage();
        }
    }
}