package com.mastercode.fitmaster.trainer.model.entity;

import com.mastercode.fitmaster.model.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class TrainerEntity {
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