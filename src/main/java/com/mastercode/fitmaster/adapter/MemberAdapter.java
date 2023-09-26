package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
public class MemberAdapter extends AbstractAdapter<Member, MemberDTO> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Member dtoToEntity(MemberDTO dto) {
        if (dto == null) return null;
        final Member entity = new Member();

        entity.setMemberID(dto.getMemberID());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setBirthDate(dto.getBirthDate());
        entity.setStatus(dto.getStatus());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        if (dto.getPassword() != null) entity.setPassword(passwordEncoder.encode(CharBuffer.wrap(dto.getPassword())));

        return entity;
    }

    @Override
    public MemberDTO entityToDTO(Member entity) {
        if (entity == null) return null;
        final MemberDTO dto = new MemberDTO();

        dto.setMemberID(entity.getMemberID());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setBirthDate(entity.getBirthDate());
        dto.setStatus(entity.getStatus());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());

        return dto;
    }

}
