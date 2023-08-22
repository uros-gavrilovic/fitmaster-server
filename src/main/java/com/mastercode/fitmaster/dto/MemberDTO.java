package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long memberID;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String address;

    private String phoneNumber;

    private LocalDate birthDate;

    private Boolean active;
}
