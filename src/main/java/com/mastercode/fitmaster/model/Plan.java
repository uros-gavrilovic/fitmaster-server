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
 * @author Uroš Gavrilović
 * Represents a fitness plan associated with a member and potentially a trainer.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {

    /**
     * The unique identifier for the fitness plan.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long planID;

    /**
     * The member associated with this fitness plan.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    /**
     * The trainer associated with this fitness plan (if any).
     */
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    /**
     * The start date and time of the fitness plan.
     */
    private LocalDateTime startsAt;

    /**
     * The end date and time of the fitness plan.
     */
    private LocalDateTime endsAt;

    /**
     * Any additional comments or notes related to the fitness plan.
     */
    private String comment;

    /**
     * The set of activities associated with this fitness plan.
     */
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Activity> activities = new HashSet<>();
}
