package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.Gender;
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
public class TrainerDTO implements Serializable {

    private Long trainerID;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String username;

    private String password;

    private String phoneNumber;

    private String address;

    private LocalDate hireDate;

}
