package com.mastercode.fitmaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercode.fitmaster.model.Trainer;
import com.mastercode.fitmaster.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TrainerControllerTest {

    @InjectMocks
    private TrainerController trainerController;
    @Mock
    private TrainerService trainerService;
    @Mock
    private MockMvc mockMvc;

    private Trainer trainer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();

        this.trainer = new Trainer();
        this.trainer.setTrainerID(1L);
        this.trainer.setFirstName("testFirstname");
        this.trainer.setLastName("testLastname");
        this.trainer.setUsername("testUsername");
        this.trainer.setPassword("testPassword");
    }

    @Test
    public void testUpdateTrainerMissingRequiredField() throws Exception {
        trainer.setFirstName(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(trainer)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateTrainerWithNonExistingID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(trainer)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trainer/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
