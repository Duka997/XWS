package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IzvjestajDTO {
    private Long id;
    private double kilometraza;
    private String commentReport;
    private Long voziloId;
    private Long oglasId;
    private Long userId;
}
