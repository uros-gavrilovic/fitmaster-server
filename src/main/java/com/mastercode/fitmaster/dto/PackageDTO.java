package com.mastercode.fitmaster.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDTO {

    private Long packageID;

    private String name;

    private Integer duration;

    private BigDecimal price;

    private Currency currency;

}
