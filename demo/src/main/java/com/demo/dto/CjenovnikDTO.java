package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CjenovnikDTO {
    private Long id;
    private double cijenaPoDanu;
    private double cijenaPoKM;
    private double cijenaCDW;
    private double popust;
    private String userUsername;
}
