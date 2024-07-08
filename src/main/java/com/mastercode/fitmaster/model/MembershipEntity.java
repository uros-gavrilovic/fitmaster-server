package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

/**
 * The MembershipEntity class represents a membership subscription of a memberEntity to a fitness package.
 *
 * @author Uroš Gavrilović
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "membership")
public class MembershipEntity {

    /** The ID of the membership. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long membershipID;

    /** The memberEntity associated with this membership. */
    @JsonBackReference
    @NotNull
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity memberEntity;

    /** The fitness package associated with this membership. */
    @OneToOne
    @NotNull
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private PackageEntity membershipPackageEntity;

    /** The start date of the membership subscription. */
    private LocalDate startDate;

    /** The end date of the membership subscription. */
    private LocalDate endDate;

    /** A transient field indicating whether the membership is currently active. */
    @Transient
    private boolean active;

    @Transient
    public boolean isActive() {
        LocalDate currentDate = LocalDate.now();
        return startDate != null && endDate != null &&
                !startDate.isAfter(currentDate) && !endDate.isBefore(currentDate);
    }
}
