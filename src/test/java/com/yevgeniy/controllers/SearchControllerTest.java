package com.yevgeniy.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearchController searchController;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void PostTokenizeShouldReturnOkResponse() throws Exception {
        mockMvc.perform(post("/api/tokenize").content("'Hello World'"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}