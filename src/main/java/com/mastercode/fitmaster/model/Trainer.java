package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Uroš Gavrilović
 * The Trainer class represents a fitness trainer.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer {
    /** Unique identifier of the trainer. Represents a primary key in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long trainerID;

    /** The first name of the trainer. */
    private String firstName;

    /** The last name of the trainer. */
    private String lastName;

    /** The gender of the trainer. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    /** The username used for authentication. */
    private String username;

    /** The password used for authentication. */
    private String password;

    /** The phone number of the trainer. */
    private String phoneNumber;

    /** The address of the trainer. */
    private String address;

    /** The date when the trainer was hired. */
    private LocalDate hireDate;
}
