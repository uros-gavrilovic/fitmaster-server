package com.mastercode.fitmaster.dto.member;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;
import java.time.LocalDate;

public record MemberSingleView(
        Long memberID,

        String firstName,

        String lastName,

        Gender gender,

        String address,

        String phoneNumber,

        LocalDate birthDate,

        MemberStatus status
) {}
