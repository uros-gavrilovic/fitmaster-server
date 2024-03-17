package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDTO {

    private Long exerciseID;

    private String name;

    private BodyPart bodyPart;

    private Category category;

    private String instructions;

}
