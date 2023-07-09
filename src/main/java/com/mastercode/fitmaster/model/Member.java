package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long memberID;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String address;

    private String phoneNumber;

    private LocalDate birthDate;

    @OneToOne(mappedBy = "member")
    private Membership membership;
}
