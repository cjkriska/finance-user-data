package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.User;
import com.charliekriska.financeuserdata.service.UserService;
import com.charliekriska.financeuserdata.utility.Samples;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testGetAllUsers() throws Exception {

        when(userService.getAllUsers())
                .thenReturn(Samples.sampleUserList());

        MvcResult result = mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<User> users = Arrays.asList(mapper.readValue(result.getResponse().getContentAsString(), User[].class));

        assertEquals(3, users.size());

        assertEquals(1, users.get(0).getUserId());
        assertEquals("sampleuser123", users.get(0).getUsername());
        assertEquals("password123", users.get(0).getPassword());

        assertEquals(2, users.get(1).getUserId());
        assertEquals("user234", users.get(1).getUsername());
        assertEquals("pw2345", users.get(1).getPassword());

        assertEquals(3, users.get(2).getUserId());
        assertEquals("user456", users.get(2).getUsername());
        assertEquals("pass45678", users.get(2).getPassword());

    }

    @Test
    void testAddUser() throws Exception {

        when(userService.addUser(any(User.class)))
                .thenReturn(Samples.sampleUserOutput());

        MvcResult result = mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsString(Samples.sampleUserInput()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        User user = mapper.readValue(result.getResponse().getContentAsString(), User.class);

        assertEquals(1, user.getUserId());
        assertEquals("sampleuser123", user.getUsername());
        assertEquals("password123", user.getPassword());

    }

}
