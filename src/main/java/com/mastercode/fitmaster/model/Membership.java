package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long membershipID;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Package membershipPackage;

    private LocalDate startDate;

    private LocalDate endDate;
}
