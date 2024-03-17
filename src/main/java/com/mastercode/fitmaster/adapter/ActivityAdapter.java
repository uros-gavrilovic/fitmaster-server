package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.ActivityDTO;
import com.mastercode.fitmaster.model.ActivityEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityAdapter extends AbstractAdapter<ActivityEntity, ActivityDTO> {

//    @Override
//    public ActivityEntity dtoToEntity(ActivityDTO dto) {
//        if (dto == null) return null;
//        final ActivityEntity entity = new ActivityEntity();
//
//        entity.setActivityID(dto.getActivityID());
//        entity.setReps(dto.getReps());
//        entity.setSets(dto.getSets());
//        entity.setComment(dto.getComment());
//
//        return entity;
//    }

    @Override
    public ActivityDTO entityToDTO(ActivityEntity entity) {
        if (entity == null) return null;
        final ActivityDTO dto = new ActivityDTO();

        dto.setActivityID(entity.getActivityID());
        dto.setReps(entity.getReps());
        dto.setSets(entity.getSets());
        dto.setComment(entity.getComment());

        return dto;
    }
}
