package com.example.springialocal.domain.api;

import com.example.springialocal.domain.api.dto.ApiDtoResponse;

public interface InvoiceApi {
    ApiDtoResponse getInvoiceByUuApi(String uuid);
}