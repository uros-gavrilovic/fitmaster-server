package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.config.UserAuthenticationProvider;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.GlobalExceptionHandler;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private MemberService memberService;

    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService, trainerService, memberService, userAuthenticationProvider))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAppInfo() throws Exception {
        Map<String, String> appInfo = new HashMap<>();
        appInfo.put("version", "1.0");

        when(userService.getAppInfo()).thenReturn(appInfo);

        mockMvc.perform(get("/app-info").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.0"));
    }

    @Test
    void testLoginTrainer() throws Exception {
        UserDTO userDTO = new UserDTO(); // UserDTO found in request body
        userDTO.setUsername("testUsername");
        userDTO.setPassword("testpassword");

        Trainer mockTrainer = new Trainer(); // Trainer linked to the username and password
        mockTrainer.setUsername("testUsername");
        mockTrainer.setToken("testToken");

        when(trainerService.login(Mockito.any(userDTO.getClass()))).thenReturn(mockTrainer);
        when(userAuthenticationProvider.createToken("testUsername")).thenReturn("testToken");

        mockMvc.perform(post("/login-trainer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUsername"))
                .andExpect(jsonPath("$.token").value("testToken"));
    }

    @Test
    void testInvalidLoginTrainerIncorrectPassword() throws Exception {
        UserDTO userDTO = new UserDTO(); // UserDTO found in request body
        userDTO.setUsername("testUsername");
        userDTO.setPassword("testIncorrectPassword");

        when(trainerService.login(Mockito.any(userDTO.getClass()))).thenThrow(new LoginException("testTitle", HttpStatus.UNAUTHORIZED));

        mockMvc.perform(post("/login-trainer").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))).andExpect(status().isUnauthorized());
    }

    @Test
    void testRegisterTrainer() throws Exception {
        Trainer trainerToBeRegistered = new Trainer();
        trainerToBeRegistered.setUsername("testUsername");
        trainerToBeRegistered.setPassword("testPassword");
        trainerToBeRegistered.setFirstName("testFirstname");
        trainerToBeRegistered.setLastName("testLastname");
        trainerToBeRegistered.setToken("testToken");

        Trainer registeredTrainer = new Trainer();
        registeredTrainer.setUsername("testUsername");
        registeredTrainer.setPassword("testPassword");
        registeredTrainer.setFirstName("testFirstname");
        registeredTrainer.setLastName("testLastname");
        registeredTrainer.setToken("testToken");

        when(trainerService.registerTrainer(Mockito.any(Trainer.class))).thenReturn(trainerToBeRegistered);
        when(userAuthenticationProvider.createToken("testUsername")).thenReturn("testToken");

        mockMvc.perform(post("/register-trainer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registeredTrainer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUsername"))
                .andExpect(jsonPath("$.token").value("testToken"));
    }

    @Test
    void testRegisterTrainerMissingFirstName() throws Exception {
        Trainer trainerToBeRegistered = new Trainer();
        trainerToBeRegistered.setUsername("testUsername");
        trainerToBeRegistered.setPassword("testPassword");
        trainerToBeRegistered.setFirstName(null);
        trainerToBeRegistered.setLastName("testLastname");

        mockMvc.perform(post("/register-trainer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainerToBeRegistered)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", Matchers.matchesPattern(".*Validation failed.*")));
    }

    @Test
    void testRegisterTrainerUsernamePatternMismatch() throws Exception {
        Trainer trainerToBeRegistered = new Trainer();
        trainerToBeRegistered.setUsername("?*/-testInvalidUsername");
        trainerToBeRegistered.setPassword("testPassword");
        trainerToBeRegistered.setFirstName("testFirstname");
        trainerToBeRegistered.setLastName("testLastname");

        mockMvc.perform(post("/register-trainer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainerToBeRegistered)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", Matchers.matchesPattern(".*Validation failed.*")));
    }
}
