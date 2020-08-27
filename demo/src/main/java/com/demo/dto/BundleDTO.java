package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BundleDTO {
    private Long id;
    private String status;
    private List<ZahtjevDTO> requests;
}
