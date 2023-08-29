package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.dto.UserDTO;
import com.mastercode.fitmaster.exception.LoginException;
import com.mastercode.fitmaster.exception.RegisterException;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.MemberService;
import com.mastercode.fitmaster.service.TrainerService;
import com.mastercode.fitmaster.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;


    @Mock
    private TrainerService trainerService;

    @Mock
    private UserService userService;

    @Mock
    private MemberService memberService;

    @Mock
    private Trainer trainer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterTrainerUsernameTaken() throws RegisterException {
        Trainer trainer = new Trainer();
        trainer.setUsername("TEST_USERNAME");

        when(trainerService.registerTrainer(any(Trainer.class))).thenThrow(new RegisterException("Username is already taken", HttpStatus.CONFLICT));

        RegisterException exception = assertThrows(RegisterException.class, () -> {
            userController.registerTrainer(trainer);
        });

        assertEquals("Username is already taken", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    public void testLoginTrainer_FailedLogin() throws Exception {
        UserDTO invalidDto = new UserDTO();
        invalidDto.setUsername("INVALID_USERNAME");
        invalidDto.setPassword("INVALID_PASSWORD");

        ObjectMapper objectMapper = new ObjectMapper();

        when(trainerService.login(any(UserDTO.class))).thenThrow(new LoginException("Wrong username or password", HttpStatus.UNAUTHORIZED));

        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.post("/login-trainer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidDto)));
        });

        assertTrue(exception instanceof ServletException);
        assertEquals("Request processing failed: com.mastercode.fitmaster.exception.LoginException: Wrong username or password", exception.getMessage());
    }
}
