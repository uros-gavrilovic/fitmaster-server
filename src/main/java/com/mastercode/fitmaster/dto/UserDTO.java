package com.mastercode.fitmaster.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class UserDTO {

    @NotEmpty
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    protected String username;

    @NotEmpty
    protected String password;

    @Transient
    protected String token;
}
