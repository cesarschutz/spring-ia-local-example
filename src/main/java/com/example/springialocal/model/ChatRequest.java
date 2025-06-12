package com.example.springialocal.model;

import jakarta.validation.constraints.NotBlank;

public class ChatRequest {
    @NotBlank(message = "A mensagem não pode ser vazia.")
    private String message;

    public ChatRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}