package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.adapter.PlanAdapter;
import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.PlanEntity;
import com.mastercode.fitmaster.repository.PlanRepository;
import com.mastercode.fitmaster.service.jooq.tables.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import static org.jooq.impl.DSL.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mastercode.fitmaster.service.jooq.tables.Trainer.TRAINER;
import static com.mastercode.fitmaster.service.jooq.tables.Plan.PLAN;


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
    public PlanDTO create(PlanEntity entity) {
        return planAdapter.entityToDTO(planRepository.saveAndFlush(entity));
    }

    @Override
    public PlanDTO update(PlanEntity entity) {
        return planAdapter.entityToDTO(planRepository.saveAndFlush(entity));
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    public Set<PlanEntity> findByTrainerId(Long trainerId) {
        return jooqService.getDslContext().select(asterisk())
                .from(PLAN)
                .where(PLAN.TRAINER_ID.eq(trainerId))
                .fetchInto(PlanEntity.class).stream().collect(Collectors.toSet());
    }

    public Set<PlanEntity> findByMemberId(Long memberId) {
        return jooqService.getDslContext()
                .select(asterisk())
                .from(PLAN)
                .where(PLAN.MEMBER_ID.eq(memberId))
                .fetchInto(PlanEntity.class).stream().collect(Collectors.toSet());
    }
}