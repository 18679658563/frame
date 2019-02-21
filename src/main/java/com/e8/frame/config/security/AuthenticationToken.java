package com.e8.frame.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthenticationToken implements Serializable {

    private final String token;
}
