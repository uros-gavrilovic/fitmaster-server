package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanAdapter implements AbstractAdapter<Plan, PlanDTO> {

    @Override
    public Plan dtoToEntity(final PlanDTO dto) {
        if (dto == null) return null;
        final Plan entity = new Plan();

        entity.setPlanID(dto.getPlanID());
        entity.setDateTime(dto.getDateTime());
        entity.setComment(dto.getComment());

        return entity;
    }

    @Override
    public PlanDTO entityToDTO(final Plan entity) {
        if (entity == null) return null;
        final PlanDTO dto = new PlanDTO();

        dto.setPlanID(entity.getPlanID());
        dto.setDateTime(entity.getDateTime());
        dto.setComment(entity.getComment());

        return dto;
    }

}
