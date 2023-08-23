package com.mastercode.fitmaster.controller;

import com.mastercode.fitmaster.model.Plan;
import com.mastercode.fitmaster.service.PlanService;
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

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlanControllerTest {

    @InjectMocks
    private PlanController planController;
    @Mock
    private PlanService planService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(planController).build();
    }

    @Test
    public void testCreatePlan() throws Exception {
        // Front end sends request in this specific JSON format
        String jsonTestData = "{\"member\":{\"memberID\":3,\"firstName\":\"Dell\",\"lastName\":\"Strosin\",\"gender\":\"MALE\",\"address\":\"015 Darlene Wall\",\"phoneNumber\":\"564.691.0922\",\"birthDate\":[1979,6,20],\"active\":true}," + "\"trainer\":{\"username\":\"a\",\"password\":\"$2a$10$2Rv9EfIRecIh6chMYas4nenW2IYF7kRKBxjPEfR1gIxkc.z3YJnmS\",\"trainerID\":0,\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":null,\"phoneNumber\":null,\"address\":null,\"hireDate\":[2023,7,30]}," + "\"startsAt\":\"2023-08-24T07:00:00.000Z\",\"endsAt\":\"2023-08-24T15:00:00.000Z\",\"exercises\":[{\"exerciseID\":1,\"name\":\"Bench Press\",\"bodyPart\":\"CHEST\",\"category\":\"BARBELL\",\"instructions\":\"Lie on a flat bench with a barbell at chest level. Grip the barbell slightly wider than shoulder-width apart. Lift the barbell off the rack and lower it to your chest. Push the barbell back up to the starting position.\",\"reps\":\"11\",\"sets\":\"11\"}]}";

        Plan mockPlan = new Plan();

        when(planService.create(any(Plan.class))).thenReturn(mockPlan);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTestData)).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testGetAllByTrainer() throws Exception {
        Long trainerId = 1L;
        Set<Plan> plans = Set.of(new Plan(), new Plan());

        when(planService.findByTrainerId(trainerId)).thenReturn(plans);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan/trainer/{id}", trainerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(plans.size()));
    }

    @Test
    public void testDeletePlan() throws Exception {
        Long planID = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plan/{id}", planID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
