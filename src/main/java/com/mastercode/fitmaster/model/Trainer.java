package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue
    @Column(name = "id")
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
