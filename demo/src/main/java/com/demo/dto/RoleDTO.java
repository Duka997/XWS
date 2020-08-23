package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class RoleDTO {
    private String username;
    private Set<String> roles;
    private Set<String> privileges;
}