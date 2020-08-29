package com.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OcjenaDTO {
    private Long id;
    private Double ocjena;
    private String userUsername;
    private Long userId;
    private Long voziloId;
    private Long oglasId;
}
