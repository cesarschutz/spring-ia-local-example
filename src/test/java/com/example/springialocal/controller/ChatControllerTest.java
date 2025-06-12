package com.example.springialocal.controller;

import com.example.springialocal.model.ChatResponse;
import com.example.springialocal.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @Test
    public void testChatEndpoint() throws Exception {
        ChatResponse mockResponse = new ChatResponse();
        mockResponse.setRole("assistant");
        mockResponse.setContent("Test response");

        when(chatService.generateResponse(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/chat")
                .content("{\"message\": \"Test prompt\"}")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("assistant"))
                .andExpect(jsonPath("$.content").value("Test response"));
    }
}