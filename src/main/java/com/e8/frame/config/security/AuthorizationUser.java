package com.e8.frame.config.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
