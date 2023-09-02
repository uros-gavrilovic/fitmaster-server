package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanAdapter extends AbstractAdapter<Plan, PlanDTO> {

    @Override
    public Plan dtoToEntity(final PlanDTO dto) {
        if (dto == null) return null;
        final Plan entity = new Plan();

        entity.setPlanID(dto.getPlanID());
        entity.setStartsAt(dto.getStartsAt());
        entity.setEndsAt(dto.getEndsAt());
        entity.setComment(dto.getComment());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    @Override
    public PlanDTO entityToDTO(final Plan entity) {
        if (entity == null) return null;
        final PlanDTO dto = new PlanDTO();

        dto.setPlanID(entity.getPlanID());
        dto.setStartsAt(entity.getStartsAt());
        dto.setEndsAt(entity.getEndsAt());
        dto.setComment(entity.getComment());
        dto.setStatus(entity.getStatus());

        return dto;
    }

}
