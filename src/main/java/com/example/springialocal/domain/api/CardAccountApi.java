package com.example.springialocal.domain.api;

import com.example.springialocal.domain.api.dto.ApiDtoResponse;

public interface CardAccountApi {
    ApiDtoResponse getCardByUuid(String uuid);

    ApiDtoResponse blockCardByUuid(String uuid);
}