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
import lombok.ToString;

/**
 * The ExerciseEntity class represents a fitness exercise.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercise")
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ExerciseEntity {

    /** The ID of the exercise. Represents primary key in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
