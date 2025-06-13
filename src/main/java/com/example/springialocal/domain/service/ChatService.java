package com.example.springialocal.domain.service;

import com.example.springialocal.application.dto.ChatResponse;
import com.example.springialocal.domain.tool.CardAccountApiTools;
import com.example.springialocal.domain.tool.InvoiceApiTools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatClient chat;

    public ChatService(OllamaChatModel model, CardAccountApiTools cardAccountApiTools, InvoiceApiTools invoiceApiTools) {
        this.chat = ChatClient.builder(model).defaultTools(cardAccountApiTools, invoiceApiTools).build();
    }

    public ChatResponse generateResponse(String prompt) {
        try {
            ChatResponse message = new ChatResponse();
            message.setRole("assistant");
            message.setContent(chat.prompt(prompt).call().content());
            return message;
        } catch (Exception e) {
            logger.error("Erro ao gerar resposta do modelo IA", e);
            return new ChatResponse("assistant", "Desculpe, ocorreu um erro ao processar sua mensagem.");
        }
    }
}