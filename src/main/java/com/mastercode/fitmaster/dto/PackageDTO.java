package com.mastercode.fitmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {

    private Long packageID;

    private String name;

    private Integer duration;

    private BigDecimal price;

    private Currency currency;

}
