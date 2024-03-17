//package com.mastercode.fitmaster.service;
//
//import com.mastercode.fitmaster.adapter.PlanAdapter;
//import com.mastercode.fitmaster.model.PlanEntity;
//import com.mastercode.fitmaster.repository.PlanRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class PlanEntityServiceTest {
//
//    @InjectMocks
//    private PlanService planService;
//
//    @Mock
//    private PlanRepository planRepository;
//
//    @Mock
//    private PlanAdapter planAdapter;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreatePlan() {
//        PlanEntity planEntity = new PlanEntity();
//
//        when(planRepository.saveAndFlush(any(PlanEntity.class))).thenReturn(planEntity);
//
//        PlanEntity createdPlanEntity = planService.create(planEntity);
//
//        assertEquals(planEntity, createdPlanEntity);
//        verify(planRepository, times(1)).saveAndFlush(any(PlanEntity.class));
//    }
//
//    @Test
//    public void testDeletePlan() {
//        Long planId = 1L;
//
//        doNothing().when(planRepository).deleteById(planId);
//
//        planService.delete(planId);
//
//        verify(planRepository, times(1)).deleteById(planId);
//    }
//}
