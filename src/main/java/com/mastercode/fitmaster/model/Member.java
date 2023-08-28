package com.mastercode.fitmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mastercode.fitmaster.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Uroš Gavrilović
 * The Member class represents a fitness club member.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"memberID", "firstName", "lastName", "gender", "phoneNumber", "birthDate", "memberships"})
public class Member {
    /** The ID of the member. Represents primary key in the database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long memberID;

    /** The first name of the member. */
    private String firstName;

    /** The last name of the member. */
    private String lastName;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;

    /** The gender of the member. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    /** The address of the member. */
    private String address;

    /** The phone number of the member. */
    private String phoneNumber;

    /** The birth date of the member. */
    private LocalDate birthDate;

    /**
     * A set of memberships associated with the member.
     *
     * @see Membership
     */
    @OneToMany(mappedBy = "member")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Membership> memberships = new HashSet<>();
}
