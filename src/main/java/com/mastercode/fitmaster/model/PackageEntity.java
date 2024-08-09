package com.mastercode.fitmaster.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Currency;

@NoArgsConstructor
@Entity
@Data
@Table(name = "package")
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long packageID;

    private String name;

    private Integer duration;

    private BigDecimal price;

    private Currency currency;

}
