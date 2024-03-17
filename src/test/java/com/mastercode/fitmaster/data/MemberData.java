package com.mastercode.fitmaster.data;

import com.mastercode.fitmaster.dto.MemberDTO;
import com.mastercode.fitmaster.model.MemberEntity;
import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;

import java.time.LocalDate;
import java.util.Set;

public class MemberData {
    public static final MemberEntity MEMBER_ENTITY = MemberEntity.builder()
            .memberID(0L)
            .firstName("John")
            .lastName("Doe")
            .gender(Gender.MALE)
            .birthDate(LocalDate.of(2000, 5, 17))
            .address("123 Main St.")
            .phoneNumber("+(123) 45-678-9012")
            .status(MemberStatus.INACTIVE)
            .membershipEntities(Set.of())
            .username("john_doe")
            .password("password")
            .build();

    public static final MemberDTO MEMBER_DTO = MemberDTO.builder()
            .memberID(0L)
            .firstName("John")
            .lastName("Doe")
            .gender(Gender.MALE)
            .birthDate(LocalDate.of(2000, 5, 17))
            .address("123 Main St.")
            .phoneNumber("+(123) 45-678-9012")
            .status(MemberStatus.INACTIVE)
            .build();

    public static final MemberEntity UPDATED_MEMBER_ENTITY = MEMBER_ENTITY.toBuilder()
            .firstName("Jane")
            .lastName("Smith")
            .gender(Gender.FEMALE)
            .build();

    public static final MemberDTO UPDATED_MEMBER_DTO = MEMBER_DTO.toBuilder()
            .firstName("Jane")
            .lastName("Smith")
            .gender(Gender.FEMALE)
            .build();
}
