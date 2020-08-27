package com.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KorpaDTO {
    private List<ZahtjevDTO> requests;
    private List<BundleDTO> bundles;
}
