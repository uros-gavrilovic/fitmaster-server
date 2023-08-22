package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The Package class represents a fitness package available for purchase.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long packageID;

    /** The name of the fitness package. */
    private String name;

    /** The price of the fitness package. */
    private BigDecimal price;
}
