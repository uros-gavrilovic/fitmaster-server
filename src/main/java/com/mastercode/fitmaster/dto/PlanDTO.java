package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.PlanStatus;
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

    private PlanStatus status;

}
