package com.demo.dto;

import lombok.*;
import org.joda.time.DateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtjevDTO {

    private Long id;
    private String mjestoPreuzimanja;
    private DateTime od;
    private DateTime doo;
    private String status;
    private Long oglasId;
    private Long bundleId;
    private Long userId;

}