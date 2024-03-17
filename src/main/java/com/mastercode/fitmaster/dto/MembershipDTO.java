package com.mastercode.fitmaster.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipDTO {

    private Long membershipID;
    
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

}
