package com.mastercode.fitmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private Integer ordinalNumber;

    private Integer reps;

    private Integer sets;

    private String comment;

}
