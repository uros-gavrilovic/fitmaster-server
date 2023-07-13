package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MembershipDTO;
import com.mastercode.fitmaster.model.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipAdapter {

    public Membership dtoToEntity(MembershipDTO dto) {
        if (dto == null)
            return null;

        final Membership entity = new Membership();
        entity.setMembershipID(dto.getMembershipID());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setActive(dto.isActive());

        return entity;
    }

    public MembershipDTO entityToDTO(Membership entity) {
        if (entity == null)
            return null;

        final MembershipDTO dto = new MembershipDTO();
        dto.setMembershipID(entity.getMembershipID());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setActive(entity.isActive());

        return dto;
    }

}