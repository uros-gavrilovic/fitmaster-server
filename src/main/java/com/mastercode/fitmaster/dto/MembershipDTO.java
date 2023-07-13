package com.mastercode.fitmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDTO {

    private Long membershipID;
    
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

}
