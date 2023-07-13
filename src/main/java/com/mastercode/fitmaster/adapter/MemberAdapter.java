package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberAdapter {

    public Member dtoToEntity(MemberDTO dto) {
        if (dto == null)
            return null;

        final Member entity = new Member();
        entity.setMemberID(dto.getMemberID());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setBirthDate(dto.getBirthDate());

        return entity;
    }

    public MemberDTO entityToDTO(Member entity) {
        if (entity == null)
            return null;

        final MemberDTO dto = new MemberDTO();
        dto.setMemberID(entity.getMemberID());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setBirthDate(entity.getBirthDate());

        return dto;
    }

}
