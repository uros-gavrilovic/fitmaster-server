package com.mastercode.fitmaster.dto;

import com.mastercode.fitmaster.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    protected String username;

    protected String password;

    protected String email;

    protected String token;

    protected Role role;

}
