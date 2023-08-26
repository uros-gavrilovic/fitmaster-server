package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PlanAdapter;
import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.Plan;
import com.mastercode.fitmaster.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlanService implements AbstractService<Plan, PlanDTO> {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanAdapter planAdapter;

    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan findByID(Long id) {
        return planRepository.getByPlanID(id);
    }

    @Override
    public List<PlanDTO> getAllDTOs() {
        return planAdapter.entitiesToDTOs(planRepository.findAll());
    }

    @Override
    public Plan create(Plan entity) {
        return planRepository.saveAndFlush(entity);
    }

    @Override
    public Plan update(Plan entity) {
        return null; // TODO
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    public Set<Plan> findByTrainerId(Long trainerId) {
        return planRepository.findByTrainerId(trainerId);
    }

    public Set<Plan> findByMemberId(Long memberId) {
        return planRepository.findByMemberId(memberId);
    }
}