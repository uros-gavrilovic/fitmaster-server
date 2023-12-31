package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO extends UserDTO implements Serializable {

    private Long trainerID;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String phoneNumber;

    private String address;

    private LocalDate hireDate;

}
