package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The Plan class represents a fitness training plan created for a member by a trainer.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long planID;

    /** The member for whom this training plan is created. */
    @OneToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    /** The trainer who created this training plan. */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    /** The start date and time of the training plan. */
    private LocalDateTime startsAt;

    /** The end date and time of the training plan. */
    private LocalDateTime endsAt;

    /** Additional comments or notes about the training plan. */
    private String comment;

    /**
     * A set of activities associated with the training plan.
     *
     * @see Activity
     */
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Activity> activities = new HashSet<>();
}
