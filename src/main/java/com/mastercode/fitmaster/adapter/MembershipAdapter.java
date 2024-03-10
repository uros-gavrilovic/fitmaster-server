package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MembershipDTO;
import com.mastercode.fitmaster.model.MembershipEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipAdapter extends AbstractAdapter<MembershipEntity, MembershipDTO> {

    @Override
    public MembershipEntity dtoToEntity(MembershipDTO dto) {
        if (dto == null) return null;
        final MembershipEntity entity = new MembershipEntity();

        entity.setMembershipID(dto.getMembershipID());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setActive(dto.isActive());

        return entity;
    }

    @Override
    public MembershipDTO entityToDTO(MembershipEntity entity) {
        if (entity == null) return null;
        final MembershipDTO dto = new MembershipDTO();

        dto.setMembershipID(entity.getMembershipID());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setActive(entity.isActive());

        return dto;
    }

    public List<MembershipDTO> entitiesToDTO(List<MembershipEntity> list) {
        if (list == null) return null;

        return list.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

}
