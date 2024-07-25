package com.example.SpringBootBoilerplate.controller;

import com.example.SpringBootBoilerplate.model.User;
import com.example.SpringBootBoilerplate.repository.UserRepository;
import com.example.SpringBootBoilerplate.security.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProtectedApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String jwtToken;

    @BeforeEach
    public void setup() throws Exception {
        userRepository.deleteAll();
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        user.setRole("ROLE_USER");
        userRepository.save(user);

        JwtRequest request = new JwtRequest();
        request.setUsername("testuser");
        request.setPassword("testpassword");

        ResultActions result = mockMvc.perform(post("/api/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        jwtToken = result.andReturn().getResponse().getContentAsString().split(":")[1].replace("\"", "").replace("}", "");
    }

    @Test
    public void testAccessProtectedApiWithoutToken() throws Exception {
        mockMvc.perform(get("/api/v1/protected"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAccessProtectedApiWithToken() throws Exception {
        mockMvc.perform(get("/api/v1/protected")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }
}
