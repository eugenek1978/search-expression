package com.yevgeniy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yevgeniy.controllers.dto.SearchRequestDto;
import com.yevgeniy.services.LexerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        mockMvc.perform(post("/api/tokens")
                .contentType("application/json")
                .content(
                        objectMapper.writeValueAsString(new SearchRequestDto())
                )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}