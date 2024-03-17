package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString
public class MemberDTO extends UserDTO implements Serializable {

    private Long memberID;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String address;

    private String phoneNumber;

    private LocalDate birthDate;

    private MemberStatus status;
}
