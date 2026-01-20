package com.molkovor.gitlabcicd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfUsers() throws Exception {
        var jsonUsers = mockMvc
                .perform(get("/api/users"))
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString();

        List<UserController.User> users = objectMapper
                .readerForListOf(UserController.User.class)
                .readValue(jsonUsers);

        Assertions.assertEquals(2, users.size());
    }
}