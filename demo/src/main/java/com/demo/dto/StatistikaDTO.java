package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatistikaDTO {
    private VoziloSaNajvecomOcenomDTO voziloSaNajvecomOcenomDTO;
    private VoziloSaNajvecomKilometrazomDTO voziloSaNajvecomKilometrazomDTO;
    private VoziloSaNajviseKomentaraDTO voziloSaNajviseKomentaraDTO;
}
