package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Exercise class represents a fitness exercise.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercises")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long exerciseID;

    /** The name of the exercise. */
    private String name;

    /** The body part targeted by the exercise. */
    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    /** The category to which the exercise belongs. */
    @Enumerated(EnumType.STRING)
    private Category category;

    /** Instructions for performing the exercise. */
    private String instructions;
}
