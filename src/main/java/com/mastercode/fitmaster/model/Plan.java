package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mastercode.fitmaster.model.enums.PlanStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a fitness plan associated with a member and potentially a trainer.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
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
    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    /**
     * The trainer associated with this fitness plan (if any).
     */
    @OneToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    /**
     * The start date and time of the fitness plan.
     */
    @Future
    @NotNull
    private LocalDateTime startsAt;

    /**
     * The end date and time of the fitness plan.
     */
    @Future
    @NotNull
    private LocalDateTime endsAt;

    /**
     * Any additional comments or notes related to the fitness plan.
     */
    private String comment;

    /**
     * The set of activities associated with this fitness plan.
     */
    @NotEmpty
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Activity> activities = new HashSet<>();

    @Nullable
    @JsonIgnore
    private Boolean completed;

    @Transient
    private PlanStatus status;

    /**
     * Determines the status of the plan based on the start and end date.
     * Only completed is being saved in the database, while the rest is calculated on the fly.
     * If plan is set as not completed, it means it has been cancelled.
     *
     * @see PlanStatus
     */
    @PostLoad
    private void determineStatus() {
        if (status == null) {
            if (completed != null && completed) {
                status = PlanStatus.COMPLETED;
            } else if (completed != null && !completed) {
                status = PlanStatus.CANCELLED;
            } else {
                status = (LocalDateTime.now().isAfter(endsAt)) ? PlanStatus.EXPIRED : PlanStatus.AWAITING;
            }
        }
    }


}
