package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoziloSaNajvecomKilometrazomDTO {
    private Long id;
    private Long markaId;
    private Long modelId;
    private String nazivMarke;
    private String model;
    private double kilometraza;
}
