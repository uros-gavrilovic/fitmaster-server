package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberID;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @JsonIgnore
    private String address;

    @JsonIgnore
    private String phoneNumber;

    @JsonIgnore
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<Membership> memberships = new HashSet<>();
}
