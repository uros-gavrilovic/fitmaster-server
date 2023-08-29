package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

/**
 * @author Uroš Gavrilović
 * The Membership class represents a membership subscription of a member to a fitness package.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "memberships")
public class Membership {
    /** The ID of the membership. */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long membershipID;

    /** The member associated with this membership. */
    @JsonBackReference
    @NotNull
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    /** The fitness package associated with this membership. */
    @OneToOne
    @NotNull
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Package membershipPackage;

    /** The start date of the membership subscription. */
    private LocalDate startDate;

    /** The end date of the membership subscription. */
    private LocalDate endDate;

    /** A transient field indicating whether the membership is currently active. */
    @Transient
    private boolean active;

    /**
     * Calculates the 'active' status of the membership based on the end date and the current date.
     */
    @PostLoad
    private void calculate() {
        this.active = endDate.isAfter(LocalDate.now());
    }
}
