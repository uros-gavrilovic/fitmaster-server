package com.mastercode.fitmaster.adapter;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
public class MemberAdapter extends AbstractAdapter<MemberEntity, MemberDTO> {
    @Override
    public MemberDTO entityToDTO(MemberEntity entity) {
        if (entity == null) return null;

        return MemberDTO.builder()
                .memberID(entity.getMemberID())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .birthDate(entity.getBirthDate())
                .status(entity.getStatus())
                .build();
    }
}
