package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PlanAdapter;
import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.PlanEntity;
import com.mastercode.fitmaster.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class PlanService implements AbstractService<PlanEntity, PlanDTO> {
    @Autowired
    private JooqService jooqService;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    PlanAdapter planAdapter;

    @Override
    public List<PlanEntity> getAll() {
        return planRepository.findAll();
    }

    @Override
    public PlanEntity findByID(Long id) {
        return planRepository.getByPlanID(id);
    }

    @Override
    public List<PlanDTO> getAllDTOs() {
        return planAdapter.entitiesToDTOs(planRepository.findAll());
    }

    @Override
    public PlanEntity create(PlanEntity entity) {
        return planRepository.saveAndFlush(entity);
    }

    @Override
    public PlanEntity update(PlanEntity entity) {
        return planRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    public Set<PlanEntity> findByTrainerId(Long trainerId) {

        //        jooqService.getDslContext().select().from(database.tables.Plan).where("trainer_id = " + trainerId).fetch();

        return planRepository.findByTrainerEntity_TrainerID(trainerId);
    }

    public Set<PlanEntity> findByMemberId(Long memberId) {
        return planRepository.findByMemberEntity_MemberID(memberId);
    }
}