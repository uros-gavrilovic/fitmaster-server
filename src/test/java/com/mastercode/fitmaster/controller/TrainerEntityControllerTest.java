//package com.mastercode.fitmaster.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mastercode.fitmaster.model.TrainerEntity;
//import com.mastercode.fitmaster.service.TrainerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//public class TrainerEntityControllerTest {
//
//    @InjectMocks
//    private TrainerController trainerController;
//    @Mock
//    private TrainerService trainerService;
//    @Mock
//    private MockMvc mockMvc;
//
//    private TrainerEntity trainerEntity;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
//
//        this.trainerEntity = new TrainerEntity();
//        this.trainerEntity.setTrainerID(1L);
//        this.trainerEntity.setFirstName("testFirstname");
//        this.trainerEntity.setLastName("testLastname");
//        this.trainerEntity.setUsername("testUsername");
//        this.trainerEntity.setPassword("testPassword");
//    }
//
//    @Test
//    public void testUpdateTrainerMissingRequiredField() throws Exception {
//        trainerEntity.setFirstName(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/trainer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(trainerEntity)))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateTrainerWithNonExistingID() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/trainer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(trainerEntity)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteTrainer() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trainer/{id}", 1L))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//}
