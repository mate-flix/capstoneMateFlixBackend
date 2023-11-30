package com.mteflix.capstonemateflixbackend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser
    public void testController () throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthRequest request = new AuthRequest();
        request.setEmail("timileyin1708@gmail.com");
        request.setFirstName("timileyin");
        request.setLastName("Tunde");
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}