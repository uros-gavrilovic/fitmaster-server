package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mastercode.fitmaster.model.enums.Gender;
import com.mastercode.fitmaster.model.enums.MemberStatus;
import com.mastercode.fitmaster.validator.annotations.NotRequiredPast;
import com.mastercode.fitmaster.validator.annotations.NotRequiredPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberEntity extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberID;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    private String address;

    @NotRequiredPhoneNumber
    private String phoneNumber;

    @NotRequiredPast
    private LocalDate birthDate;

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JsonManagedReference
    private Set<MembershipEntity> membershipEntities = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    /**
     * Function that returns the latest membership of the member.
     * If the member doesn't have any membership, null will be returned.
     *
     * @return membership with latest field "startDate".
     */
    @Transient
    public MembershipEntity getLatestMembership() {
        return membershipEntities.stream().max(Comparator.comparing(MembershipEntity::getStartDate)).orElse(null);
    }

    /**
     * Determines the status of the member based on the membershipEntities.
     * Only banned status will be saved to the database. Everything else will be calculated on the fly.
     *
     * @see MemberStatus
     */
    @PostLoad
    private void determineStatus() {
        if (status == null) {
            if (membershipEntities.isEmpty()) {
                status = MemberStatus.PENDING; // Freshly created member doesn't have any membership.
            } else {
                status = membershipEntities.stream()
                        .anyMatch(MembershipEntity::isActive) ? MemberStatus.ACTIVE : MemberStatus.INACTIVE;
            }
        }
    }

}
