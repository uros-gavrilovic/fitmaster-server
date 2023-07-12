package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.model.Plan;
import com.mastercode.fitmaster.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanService {
    @Autowired
    PlanRepository planRepository;

    public List<Plan> getAll() {
        return planRepository.findAll();
    }
}