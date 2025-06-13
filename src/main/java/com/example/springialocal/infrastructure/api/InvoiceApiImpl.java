package com.example.springialocal.infrastructure.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springialocal.domain.api.InvoiceApi;
import com.example.springialocal.domain.api.dto.ApiDtoResponse;
import com.example.springialocal.infrastructure.util.RestTemplateUtil;

@Service
public class InvoiceApiImpl extends RestTemplateUtil implements InvoiceApi  {

    public InvoiceApiImpl(@Value("${app.invoice-api.base-url}") String baseUrl) {
        super(baseUrl);
    }

    public ApiDtoResponse getInvoiceByUuApi(String uuid) {
        return get("/invoices/" + uuid);
    }
}