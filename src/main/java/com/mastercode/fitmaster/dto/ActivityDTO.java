package com.mastercode.fitmaster.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO {

    private Long activityID;

    private Integer reps;

    private Integer sets;

    private String comment;

}
