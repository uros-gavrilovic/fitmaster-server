package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.validator.annotations.NotRequiredPast;
import com.mastercode.fitmaster.validator.annotations.NotRequiredPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The Trainer class represents a fitness trainer.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer extends User {
    /** Unique identifier of the trainer. Represents a primary key in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long trainerID;

    /** The first name of the trainer. */
    @NotEmpty
    private String firstName;

    /** The last name of the trainer. */
    @NotEmpty
    private String lastName;

    /** The gender of the trainer. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    /** The phone number of the trainer. */
    @NotRequiredPhoneNumber
    private String phoneNumber;

    /** The address of the trainer. */
    private String address;

    /** The date when the trainer was hired. */
    @NotRequiredPast
    private LocalDate hireDate;
}
