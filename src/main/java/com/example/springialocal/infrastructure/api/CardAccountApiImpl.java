package com.example.springialocal.infrastructure.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springialocal.domain.api.CardAccountApi;
import com.example.springialocal.domain.api.dto.ApiDtoResponse;
import com.example.springialocal.infrastructure.util.RestTemplateUtil;

@Service
public class CardAccountApiImpl extends RestTemplateUtil implements CardAccountApi  {

    public CardAccountApiImpl(@Value("${app.card-account-api.base-url}") String baseUrl) {
        super(baseUrl);
    }

    public ApiDtoResponse getCardByUuid(String uuid) {
        return get("/cards/" + uuid);
    }

    public ApiDtoResponse blockCardByUuid(String uuid) {
        return post("/cards/block/" + uuid, null);
    }
}