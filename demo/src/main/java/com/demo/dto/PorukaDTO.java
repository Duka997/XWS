package com.demo.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorukaDTO {

    private Long id;

    private String sadrzaj;

    private Long posiljalacId;

    private Long primalacId;
}
