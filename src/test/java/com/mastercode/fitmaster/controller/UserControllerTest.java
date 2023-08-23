package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.dto.TrainerDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;

    @MockBean
    private UserService userService;

    @MockBean
    private MemberService memberService;

    private UserController userController;

    private TrainerDTO trainerDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainerDTO = new TrainerDTO();
        trainerService = mock(TrainerService.class);
        userController = new UserController(userService, trainerService, memberService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterTrainerUsernameTaken() throws RegisterException {
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setUsername("TEST_USERNAME");

        when(trainerService.registerTrainer(any(TrainerDTO.class))).thenThrow(new RegisterException("Username is already taken", HttpStatus.CONFLICT));

        RegisterException exception = assertThrows(RegisterException.class, () -> {
            userController.registerTrainer(trainerDTO);
        });

        assertEquals("Username is already taken", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    public void testLoginTrainer_FailedLogin() throws Exception {
        TrainerDTO invalidDto = new TrainerDTO();
        invalidDto.setUsername("INVALID_USERNAME");
        invalidDto.setPassword("INVALID_PASSWORD");

        ObjectMapper objectMapper = new ObjectMapper();

        when(trainerService.login(any(TrainerDTO.class))).thenThrow(new LoginException("Wrong username or password", HttpStatus.UNAUTHORIZED));

        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.post("/login-trainer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidDto)));
        });

        assertTrue(exception instanceof ServletException);
        assertEquals("Request processing failed: com.mastercode.fitmaster.exception.LoginException: Wrong username or password", exception.getMessage());
    }
}
