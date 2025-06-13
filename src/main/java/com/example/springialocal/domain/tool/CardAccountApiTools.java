package com.example.springialocal.domain.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.example.springialocal.domain.api.CardAccountApi;
import com.example.springialocal.domain.api.dto.ApiDtoResponse;

@Component
public class CardAccountApiTools {
    private static final Logger logger = LoggerFactory.getLogger(CardAccountApiTools.class);

    private final CardAccountApi cardAccountApi;

    public CardAccountApiTools(CardAccountApi cardAccountApi) {
        this.cardAccountApi = cardAccountApi;
    }

    @Tool(
        name = "get_card_by_uuid",
        description = "Consulta dados de um cartão através do seu UUID.")
    public ApiDtoResponse getCardByUuid(String uuid) {
        logger.info("---> tool:get_card_by_uuid invoked with UUID: {}", uuid);
        return cardAccountApi.getCardByUuid(uuid);
    }

    @Tool(
        name = "block_card_by_uuid",
        description = "Bloqueia um cartão através do seu UUID.")
    public ApiDtoResponse blockCardByUuid(String uuid) {
        logger.info("---> tool:block_card_by_uuid invoked with uuid: {}", uuid);
        return cardAccountApi.blockCardByUuid(uuid);
    }
}