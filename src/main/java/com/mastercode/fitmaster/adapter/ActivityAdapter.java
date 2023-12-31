package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.ActivityDTO;
import com.mastercode.fitmaster.model.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityAdapter extends AbstractAdapter<Activity, ActivityDTO> {

    @Override
    public Activity dtoToEntity(ActivityDTO dto) {
        if (dto == null) return null;
        final Activity entity = new Activity();

        entity.setActivityID(dto.getActivityID());
        entity.setReps(dto.getReps());
        entity.setSets(dto.getSets());
        entity.setComment(dto.getComment());

        return entity;
    }

    @Override
    public ActivityDTO entityToDTO(Activity entity) {
        if (entity == null) return null;
        final ActivityDTO dto = new ActivityDTO();

        dto.setActivityID(entity.getActivityID());
        dto.setReps(entity.getReps());
        dto.setSets(entity.getSets());
        dto.setComment(entity.getComment());

        return dto;
    }
}
