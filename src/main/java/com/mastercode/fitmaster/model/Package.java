package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Uroš Gavrilović
 * The Package class represents a fitness package available for purchase.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "packages")
public class Package {
    /** The ID of the fitness package. */
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long packageID;

    /** The name of the fitness package. */
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    /** The price of the fitness package. */ private BigDecimal price;
}
