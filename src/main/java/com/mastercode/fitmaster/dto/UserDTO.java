package com.mastercode.fitmaster.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mastercode.fitmaster.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({@JsonSubTypes.Type(value = MemberDTO.class, name = "MEMBER"), @JsonSubTypes.Type(value = TrainerDTO.class, name = "TRAINER")})
public class UserDTO {

    protected String username;

    protected String password;

    protected String email;

    protected String token;

    protected Role role;

}
