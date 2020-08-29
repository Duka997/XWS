package com.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KomentarDTO {
    private Long id;
    private  String tekst;
    private boolean odobren;
    private VoziloDTO vozilo;
    private Long userId;
    private String userUsername;
}
