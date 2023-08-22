package com.mastercode.fitmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO implements Serializable {

    private Long planID;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    private String comment;

}
