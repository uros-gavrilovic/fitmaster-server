package com.mastercode.fitmaster.model;

import com.mastercode.fitmaster.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberID;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String phoneNumber;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "member")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<Membership> memberships = new HashSet<>();
}
