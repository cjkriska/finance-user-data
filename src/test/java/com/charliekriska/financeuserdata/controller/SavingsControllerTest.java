package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.model.User;
import com.charliekriska.financeuserdata.service.SavingsService;
import com.charliekriska.financeuserdata.utility.Samples;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SavingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavingsService savingsService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testGetSavingsByUserId() throws Exception {

        when(savingsService.getSavingsByUserId(anyInt())).thenReturn(Samples.savingsSampleOutput());

        MvcResult result = mockMvc.perform(get("/savings/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Savings savings = mapper.readValue(result.getResponse().getContentAsString(), Savings.class);

        assertEquals(savings.getUserId(), 1);
        assertEquals(savings.getSavingsDataId(), 2);
        assertEquals(savings.getIncome(), 50000);

    }
    @Test
    void testAddSavings() throws Exception {

        when(savingsService.addSavings(any(Savings.class)))
                .thenReturn(Samples.savingsSampleOutput());

        MvcResult result = mockMvc.perform(post("/savings")
                        .content(mapper.writeValueAsString(Samples.savingsSampleInput()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Savings savings = mapper.readValue(result.getResponse().getContentAsString(), Savings.class);

        assertEquals(savings.getUserId(), 1);
        assertEquals(savings.getSavingsDataId(), 2);
        assertEquals(savings.getIncome(), 50000);

    }

}
