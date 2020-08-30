package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RacunDTO {
    private Long id;
    private double cijena;
    private Boolean paid;
    private UserDTO user;
}
