package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.BodyPart;
import com.mastercode.fitmaster.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private Long exerciseID;

    private String name;

    private BodyPart bodyPart;

    private Category category;

    private String instructions;

}
