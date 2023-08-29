package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Uroš Gavrilović
 * The Exercise class represents a fitness exercise.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercises")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {
    /** The ID of the exercise. Represents primary key in the database. */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long exerciseID;

    /** The name of the exercise. */
    @NotEmpty
    private String name;

    /** The body part targeted by the exercise. */
    @NotNull
    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    /** The category to which the exercise belongs. */
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    /** Instructions for performing the exercise. */
    private String instructions;
}
