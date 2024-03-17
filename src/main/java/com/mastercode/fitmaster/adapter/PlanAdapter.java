package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.PlanDTO;
import com.mastercode.fitmaster.model.PlanEntity;
import com.microsoft.azure.management.compute.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanAdapter extends AbstractAdapter<PlanEntity, PlanDTO> {
    @Override
    public PlanDTO entityToDTO(final PlanEntity entity) {
        if (entity == null) return null;

        return PlanDTO.builder()
                .planID(entity.getPlanID())
                .startsAt(entity.getStartsAt())
                .endsAt(entity.getEndsAt())
                .comment(entity.getComment())
                .status(entity.getStatus())
                .build();
    }
}
