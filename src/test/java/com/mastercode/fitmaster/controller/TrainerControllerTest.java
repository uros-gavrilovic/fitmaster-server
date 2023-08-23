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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TrainerControllerTest {

    @InjectMocks
    private TrainerController trainerController;
    @Mock
    private TrainerService trainerService;
    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
    }

    @Test
    public void testUpdateTrainer() throws Exception {
        Trainer trainer = new Trainer();
        trainer.setTrainerID(1L);
        trainer.setFirstName("TEST_UPDATED_FIRSTNAME");
        trainer.setLastName("TEST_UPDATED_LASTNAME");

        when(trainerService.update(any(Trainer.class))).thenReturn(trainer);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(trainer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("TEST_UPDATED_FIRSTNAME"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("TEST_UPDATED_LASTNAME"));
    }

    @Test
    public void testDeleteTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trainer/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
