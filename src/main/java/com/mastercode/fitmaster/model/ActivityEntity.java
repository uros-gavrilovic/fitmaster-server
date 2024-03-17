package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mastercode.fitmaster.adapter.ActivityAdapter;
import com.mastercode.fitmaster.dto.ActivityDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The ActivityEntity class represents a fitness activity within a training planEntity.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "activity")
public class ActivityEntity {

    /** The ordinal number of the activity within the training planEntity, also represents primary key. */
    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long activityID;

    /** The training planEntity to which this activity belongs. */
    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private PlanEntity planEntity;

    /** The exerciseEntity associated with this activity. */
    @NotNull
    @OneToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exerciseEntity;

    /** The number of repetitions for the exerciseEntity. */
    @NotNull
    @Positive
    private Integer reps;

    /** The number of sets for the exerciseEntity. */
    @NotNull
    @Positive
    private Integer sets;

    /** Additional comments or notes about the activity. */
    private String comment;

}
