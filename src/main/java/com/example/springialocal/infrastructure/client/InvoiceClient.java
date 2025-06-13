package com.example.springialocal.infrastructure.client;

import com.example.springialocal.domain.api.dto.ApiDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invoiceApi", url = "${app.invoice-api.base-url}")
public interface InvoiceClient {
    @GetMapping(value = "${app.invoice-api.get-invoice-by-uuid}")
    ApiDtoResponse getInvoiceByUuid(@PathVariable("uuid") String uuid);
}