package com.directrice.banking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class BankingControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenValid_WhenAdded_shouldReturnValidResponse() throws Exception {

        MvcResult result = this.mockMvc.perform(post("/directrice/banking")

                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

}
