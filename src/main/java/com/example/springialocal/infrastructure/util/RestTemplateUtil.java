package com.example.springialocal.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springialocal.domain.api.dto.ApiDtoResponse;

@Component
public abstract class RestTemplateUtil {
    
    private static final String ERROR_MSG = "Erro ao integrar com API.";
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);
    private final String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    protected RestTemplateUtil(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected ApiDtoResponse get(String pathSegment) {
        try {
            return createReturn(restTemplate.getForEntity(createUrl(pathSegment), String.class));
        } catch (RestClientException e) {
            return handleError(e);
        }
    }

    protected ApiDtoResponse post(String path, Object body) {
        try {
            return createReturn(restTemplate.postForEntity(createUrl(path), body, String.class));
        } catch (RestClientException e) {
            return handleError(e);
        }
    }

    private String createUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment(path).build().toUriString();
    }

    private ApiDtoResponse handleError(RestClientException e) {
        logger.error(ERROR_MSG, e.getMessage());
        return new ApiDtoResponse("unknown_error", ERROR_MSG);
    }

    private ApiDtoResponse createReturn(ResponseEntity<String> response) {
        return new ApiDtoResponse(response.getStatusCode().toString(), response.getBody());
    }
}