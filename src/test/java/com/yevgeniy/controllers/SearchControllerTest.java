package com.yevgeniy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yevgeniy.controllers.dto.SearchRequestDto;
import com.yevgeniy.services.LexerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(SearchController.class)
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SearchController searchController;

    @MockBean
    private LexerService lexerService;

    @Test
    void PostTokenizeShouldReturnOkResponse() throws Exception {
        when(lexerService.tokenize(anyString())).thenAnswer(str -> List.of(str));
        mockMvc.perform(post("/api/tokens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new SearchRequestDto())
                )
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.").value("Default"));
    }
}