package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Uroš Gavrilović
 * The Activity class represents a fitness activity within a training plan.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {
    /** The ordinal number of the activity within the training plan, also represents primary key. */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinalNumber")
    private Integer ordinalNumber;

    /** The training plan to which this activity belongs. */
    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    /** The exercise associated with this activity. */
    @NotNull
    @OneToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    /** The number of repetitions for the exercise. */
    @NotNull
    @Positive
    private Integer reps;

    /** The number of sets for the exercise. */
    @NotNull
    @Positive
    private Integer sets;

    /** Additional comments or notes about the activity. */
    private String comment;
}
